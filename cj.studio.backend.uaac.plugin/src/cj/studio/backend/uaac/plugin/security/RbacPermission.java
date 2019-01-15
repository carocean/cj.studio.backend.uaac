package cj.studio.backend.uaac.plugin.security;

import java.util.ArrayList;
import java.util.List;

import cj.lns.chip.sos.cube.framework.ICube;
import cj.lns.chip.sos.cube.framework.IDocument;
import cj.lns.chip.sos.cube.framework.IQuery;
import cj.lns.chip.sos.cube.framework.TupleDocument;
import cj.studio.backend.uaac.security.PermissionBO;
import cj.studio.backend.uaac.security.service.IRbacPermission;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;

@CjService(name = "rbacPermission")
public class RbacPermission implements IRbacPermission {
	@CjServiceRef(refByName = "mongodb.netos.uaac")
	ICube uaac;

	@Override
	public String assign(String oid, String ocode, String opid, String opcode) throws Exception {
		PermissionBO bo = find(ocode, opcode);
		if (bo != null) {
			bo.setObjid(oid);
			bo.setOperid(opid);
			uaac.updateDoc("rbac.permission", bo.getId(), new TupleDocument<>(bo));
			return bo.getId();
		}
		bo = new PermissionBO();
		bo.setObjcode(ocode);
		bo.setObjid(oid);
		bo.setOpercode(opcode);
		bo.setOperid(opid);
		return uaac.saveDoc("rbac.permission", new TupleDocument<PermissionBO>(bo));
	}

	@Override
	public boolean exists(String ocode, String opcode) {
		return uaac.tupleCount("rbac.permission",
				String.format("{'tuple.objcode':'%s','tuple.opercode':'%s'}", ocode, opcode)) > 0;
	}

	@Override
	public PermissionBO find(String ocode, String opcode) {
		String cjql = String.format(
				"select {'tuple':'*'} from tuple rbac.permission %s where {'tuple.objcode':'%s','tuple.opercode':'%s'}",
				PermissionBO.class.getName(), ocode, opcode);
		IQuery<PermissionBO> q = uaac.createQuery(cjql);
		IDocument<PermissionBO> doc = q.getSingleResult();
		if (doc == null)
			return null;
		PermissionBO bo = doc.tuple();
		bo.setId(doc.docid());
		return bo;
	}

	@Override
	public void unassign(String oid, String opid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(String id) {
		uaac.deleteDoc("rbac.permission", id);
	}

	@Override
	public List<PermissionBO> permissions(String oid) {
		String cjql = String.format("select {'tuple':'*'} from tuple rbac.permission %s where {'tuple.objid':'?(oid)'}",
				PermissionBO.class.getName());
		IQuery<PermissionBO> q = uaac.createQuery(cjql);
		q.setParameter("oid", oid);
		List<PermissionBO> permissions = new ArrayList<>();
		List<IDocument<PermissionBO>> list = q.getResultList();
		for (IDocument<PermissionBO> tuple : list) {
			PermissionBO u = tuple.tuple();
			u.setId(tuple.docid());
			permissions.add(u);
		}
		return permissions;
	}

	@Override
	public List<PermissionBO> permissions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PermissionBO find(String pid) {
		// TODO Auto-generated method stub
		return null;
	}

}
