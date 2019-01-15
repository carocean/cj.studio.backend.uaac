package cj.studio.backend.uaac.plugin.security;

import java.util.ArrayList;
import java.util.List;

import cj.lns.chip.sos.cube.framework.ICube;
import cj.lns.chip.sos.cube.framework.IDocument;
import cj.lns.chip.sos.cube.framework.IQuery;
import cj.lns.chip.sos.cube.framework.TupleDocument;
import cj.studio.backend.uaac.security.UaBO;
import cj.studio.backend.uaac.security.service.IRbacUA;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;

@CjService(name = "rbacUA")
public class RbacUA implements IRbacUA {
	@CjServiceRef(refByName = "mongodb.netos.uaac")
	ICube uaac;

	@Override
	public boolean hasRole(String uid, String roleid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String add(UaBO bo) {
		UaBO boexists = find(bo.getRcode(), bo.getUcode());
		if (boexists != null) {
			boexists.setRid(bo.getRid());
			boexists.setUid(bo.getUid());
			uaac.updateDoc("rbac.ua", boexists.getId(), new TupleDocument<>(boexists));
			return boexists.getId();
		}
		return uaac.saveDoc("rbac.ua", new TupleDocument<UaBO>(bo));
	}

	@Override
	public List<String> findUCodesByRCode(String rcode) {
		String cjql = String.format("select {'tuple':'*'} from tuple rbac.ua %s where {'tuple.rcode':'%s'}",
				UaBO.class.getName(), rcode);
		IQuery<UaBO> q = uaac.createQuery(cjql);
		List<String> list = new ArrayList<>();
		List<IDocument<UaBO>> rlist = q.getResultList();
		for (IDocument<UaBO> doc : rlist) {
			list.add(doc.tuple().getUcode());
		}
		return list;
	}

	@Override
	public UaBO find(String rcode, String ucode) {
		String cjql = String.format(
				"select {'tuple':'*'} from tuple rbac.ua %s where {'tuple.rcode':'%s','tuple.ucode':'%s'}",
				UaBO.class.getName(), rcode, ucode);
		IQuery<UaBO> q = uaac.createQuery(cjql);
		IDocument<UaBO> doc = q.getSingleResult();
		if (doc == null)
			return null;
		UaBO bo = doc.tuple();
		bo.setId(doc.docid());
		return bo;
	}

	@Override
	public void remove(String ua_id) {
		uaac.deleteDoc("rbac.ua", ua_id);
	}

	@Override
	public void unassignAllByUser(String uid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unassignAllByRole(String rid) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<UaBO> pasByRoleId(String roleid) {
		String cjql = String.format("select {'tuple':'*'} from tuple rbac.ua %s where {'tuple.rid':'%s'}",
				UaBO.class.getName(), roleid);
		IQuery<UaBO> q = uaac.createQuery(cjql);
		List<UaBO> ualist = new ArrayList<>();
		List<IDocument<UaBO>> list = q.getResultList();
		for (IDocument<UaBO> tuple : list) {
			UaBO u = tuple.tuple();
			u.setId(tuple.docid());
			ualist.add(u);
		}
		return ualist;
	}

	@Override
	public List<UaBO> pasByUserId(String uid) {
		String cjql = String.format("select {'tuple':'*'} from tuple rbac.ua %s where {'tuple.uid':'%s'}",
				UaBO.class.getName(), uid);
		IQuery<UaBO> q = uaac.createQuery(cjql);
		List<UaBO> ualist = new ArrayList<>();
		List<IDocument<UaBO>> list = q.getResultList();
		for (IDocument<UaBO> tuple : list) {
			UaBO u = tuple.tuple();
			u.setId(tuple.docid());
			ualist.add(u);
		}
		return ualist;
	}

}
