package cj.studio.backend.uaac.plugin.security;

import java.util.ArrayList;
import java.util.List;

import cj.lns.chip.sos.cube.framework.ICube;
import cj.lns.chip.sos.cube.framework.IDocument;
import cj.lns.chip.sos.cube.framework.IQuery;
import cj.lns.chip.sos.cube.framework.TupleDocument;
import cj.studio.backend.uaac.security.PaBO;
import cj.studio.backend.uaac.security.service.IRbacPA;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;

@CjService(name = "rbacPa")
public class RbacPA implements IRbacPA {
	@CjServiceRef(refByName = "mongodb.netos.uaac")
	ICube home;

	@Override
	public boolean hasPermission(String roleid, String permid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void unassignPermission(String roleid, String permid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unassignAllPermissionByPermId(String permid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unassignAllPermissionByRole(String roleid) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PaBO> pasByRoleId(String roleid) {
		String cjql = String.format("select {'tuple':'*'} from tuple rbac.pa %s where {'tuple.roleid':'%s'}",
				PaBO.class.getName(), roleid);
		IQuery<PaBO> q = home.createQuery(cjql);
		List<PaBO> palist = new ArrayList<>();
		List<IDocument<PaBO>> list = q.getResultList();
		for (IDocument<PaBO> tuple : list) {
			PaBO u = tuple.tuple();
			u.setId(tuple.docid());
			palist.add(u);
		}
		return palist;
	}

	@Override
	public String add(PaBO bo) {
		PaBO boexists = find(bo.getRolecode(), bo.getPermcode());
		if (boexists != null) {
			boexists.setRoleid(bo.getRoleid());
			boexists.setPermid(bo.getPermid());
			home.updateDoc("rbac.pa", boexists.getId(), new TupleDocument<>(boexists));
			return boexists.getId();
		}
		return home.saveDoc("rbac.pa", new TupleDocument<PaBO>(bo));
	}

	@Override
	public PaBO find(String rolecode, String permcode) {
		String cjql = String.format(
				"select {'tuple':'*'} from tuple rbac.pa %s where {'tuple.rolecode':'%s','tuple.permcode':'%s'}",
				PaBO.class.getName(), rolecode, permcode);
		IQuery<PaBO> q = home.createQuery(cjql);
		IDocument<PaBO> doc = q.getSingleResult();
		if (doc == null)
			return null;
		PaBO bo = doc.tuple();
		bo.setId(doc.docid());
		return bo;
	}

	@Override
	public void remove(String pa_id) {
		home.deleteDoc("rbac.pa", pa_id);
	}

}
