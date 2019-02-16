package cj.studio.backend.uacc.plugin.security;

import java.util.ArrayList;
import java.util.List;

import cj.lns.chip.sos.cube.framework.ICube;
import cj.lns.chip.sos.cube.framework.IDocument;
import cj.lns.chip.sos.cube.framework.IQuery;
import cj.lns.chip.sos.cube.framework.TupleDocument;
import cj.studio.backend.uacc.security.Permission;
import cj.studio.backend.uacc.security.service.IRbacPermission;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;

@CjService(name = "rbacPermission")
public class RbacPermission implements IRbacPermission {
	@CjServiceRef(refByName = "mongodb.netos.uacc")
	ICube uacc;

	@Override
	public String assign(String objcode, String permcode, String permname) {
		if (exists(objcode, permcode)) {
			throw new RuntimeException(String.format("对象:%s 已存在许可:%s.", objcode, permcode));
		}
		Permission perm = new Permission(null, permcode, objcode, permname);
		return uacc.saveDoc("rbac.permission", new TupleDocument<>(perm));
	}

	@Override
	public void unassign(String objcode, String permcode) {
		Permission perm = find(objcode, permcode);
		if (perm == null)
			return;
		remove(perm.getId());
	}

	@Override
	public void remove(String permid) {
		uacc.deleteDoc("rbac.permission", permid);
	}

	@Override
	public List<Permission> permissions(String objcode) {
		String cjql = String.format(
				"select {'tuple':'*'} from tuple rbac.permission %s where {'tuple.objcode':'?(objcode)'}",
				Permission.class.getName());
		IQuery<Permission> q = uacc.createQuery(cjql);
		q.setParameter("objcode", objcode);
		List<Permission> permissions = new ArrayList<>();
		List<IDocument<Permission>> list = q.getResultList();
		for (IDocument<Permission> tuple : list) {
			Permission u = tuple.tuple();
			u.setId(tuple.docid());
			permissions.add(u);
		}
		return permissions;
	}

	@Override
	public Permission find(String objcode, String permcode) {
		String cjql = String.format(
				"select {'tuple':'*'} from tuple rbac.permission %s where {'tuple.objcode':'%s','tuple.permcode':'%s'}",
				Permission.class.getName(), objcode, permcode);
		IQuery<Permission> q = uacc.createQuery(cjql);
		IDocument<Permission> doc = q.getSingleResult();
		if (doc == null)
			return null;
		Permission bo = doc.tuple();
		bo.setId(doc.docid());
		return bo;
	}

	@Override
	public Permission find(String permid) {
		String cjql = String.format("select {'tuple':'*'} from tuple rbac.permission %s where {'_id':ObjectId('%s')}",
				Permission.class.getName(), permid);
		IQuery<Permission> q = uacc.createQuery(cjql);
		IDocument<Permission> doc = q.getSingleResult();
		if (doc == null)
			return null;
		doc.tuple().setId(doc.docid());
		return doc.tuple();
	}

	@Override
	public boolean exists(String objcode, String permcode) {
		return uacc.tupleCount("rbac.permission",
				String.format("{'tuple.objcode':'%s','tuple.permcode':'%s'}", objcode, permcode)) > 0;
	}

}
