package cj.studio.backend.uacc.plugin.security;

import java.util.ArrayList;
import java.util.List;

import cj.lns.chip.sos.cube.framework.ICube;
import cj.lns.chip.sos.cube.framework.IDocument;
import cj.lns.chip.sos.cube.framework.IQuery;
import cj.lns.chip.sos.cube.framework.TupleDocument;
import cj.studio.backend.uacc.security.PA;
import cj.studio.backend.uacc.security.Permission;
import cj.studio.backend.uacc.security.service.IRbacPA;
import cj.studio.backend.uacc.security.service.IRbacPermission;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.ultimate.util.StringUtil;

@CjService(name = "rbacPA")
public class RbacPA implements IRbacPA {
	@CjServiceRef(refByName = "mongodb.netos.uacc")
	ICube uacc;
	@CjServiceRef(refByName = "rbacPermission")
	IRbacPermission rbacPermission;

	@Override
	public List<PA> listByPrincipal(String princode) {
		String cjql = String.format("select {'tuple':'*'} from tuple rbac.pa %s where {'tuple.princode':'%s'}",
				PA.class.getName(), princode);
		IQuery<PA> q = uacc.createQuery(cjql);
		List<PA> palist = new ArrayList<>();
		List<IDocument<PA>> list = q.getResultList();
		for (IDocument<PA> tuple : list) {
			PA u = tuple.tuple();
			u.setId(tuple.docid());
			palist.add(u);
		}
		return palist;
	}

	@Override
	public List<PA> listByObject(String objcode) {
		List<Permission> list = rbacPermission.permissions(objcode);
		List<String> ids = new ArrayList<>();
		for (Permission p : list) {
			ids.add("'" + p.getId() + "'");
		}
		String cjql = String.format("select {'tuple':'*'} from tuple rbac.pa %s where {'tuple.permid':{'$in':%s}}",
				PA.class.getName(), ids);
		IQuery<PA> q = uacc.createQuery(cjql);
		List<PA> palist = new ArrayList<>();
		List<IDocument<PA>> relist = q.getResultList();
		for (IDocument<PA> tuple : relist) {
			PA u = tuple.tuple();
			u.setId(tuple.docid());
			palist.add(u);
		}
		return palist;
	}

	@Override
	public List<PA> list(String princode, String objcode) {
		List<Permission> list = rbacPermission.permissions(objcode);
		List<String> ids = new ArrayList<>();
		for (Permission p : list) {
			ids.add("'" + p.getId() + "'");
		}
		String cjql = String.format(
				"select {'tuple':'*'} from tuple rbac.pa %s where {'tuple.princode':'%s','tuple.permid':{'$in':%s}}",
				PA.class.getName(), princode, ids);
		IQuery<PA> q = uacc.createQuery(cjql);
		List<PA> palist = new ArrayList<>();
		List<IDocument<PA>> relist = q.getResultList();
		for (IDocument<PA> tuple : relist) {
			PA u = tuple.tuple();
			u.setId(tuple.docid());
			palist.add(u);
		}
		return palist;
	}

	@Override
	public String add(PA pa) {
		if(StringUtil.isEmpty(pa.getPermid())) {
			throw new RuntimeException(String.format("许可标识为空"));
		}
		Permission perm = rbacPermission.find(pa.getPermid());
		if(perm==null) {
			throw new RuntimeException(String.format("许可不存在：%s", pa.getPermid()));
		}
		PA old = find(pa.getPrincode(), pa.getPermid());
		if (old != null) {
			perm = rbacPermission.find(old.getPermid());
			throw new RuntimeException(String.format("已存在授权：principal:%s,objcode:%s,permcode:%s", old.getPrincode(),
					perm.getObjcode(), perm.getPermcode()));
		}
		return uacc.saveDoc("rbac.pa", new TupleDocument<>(pa));
	}

	@Override
	public PA find(String princode, String permid) {
		String cjql = String.format(
				"select {'tuple':'*'} from tuple rbac.pa %s where {'tuple.princode':'%s','tuple.permid':'%s'}",
				PA.class.getName(), princode, permid);
		IQuery<PA> q = uacc.createQuery(cjql);
		IDocument<PA> doc = q.getSingleResult();
		if (doc == null)
			return null;
		doc.tuple().setId(doc.docid());
		return doc.tuple();
	}

	@Override
	public void remove(String paid) {
		uacc.deleteDoc("rbac.pa", paid);
	}

	@Override
	public PA find(String princode, String objcode, String permcode) {
		Permission perm = rbacPermission.find(objcode, permcode);
		if (perm == null)
			return null;
		return find(princode, perm.getId());
	}

	@Override
	public boolean exists(String princode, String objcode, String permcode) {
		Permission perm = rbacPermission.find(objcode, permcode);
		if (perm == null)
			return false;
		return uacc.tupleCount("rbac.pa",
				String.format("{'tuple.princode':'%s','tuple.permid':'%s'}", princode, perm.getId())) > 0;
	}

}
