package cj.studio.backend.uacc.program.stub;

import java.util.List;

import cj.studio.backend.uacc.security.ACE;
import cj.studio.backend.uacc.security.ACL;
import cj.studio.backend.uacc.security.PA;
import cj.studio.backend.uacc.security.Permission;
import cj.studio.backend.uacc.security.service.IRbacPA;
import cj.studio.backend.uacc.security.service.IRbacPermission;
import cj.studio.backend.uacc.security.stub.IACLStub;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.gateway.stub.GatewayAppSiteRestStub;

@CjService(name = "/acl.service")
public class ACLStub extends GatewayAppSiteRestStub  implements IACLStub {
	@CjServiceRef(refByName = "uaccplugin.rbacPA")
	IRbacPA rbacPA;
	@CjServiceRef(refByName = "uaccplugin.rbacPermission")
	IRbacPermission rbacPermission;

	@Override
	public ACL getACLByPrincipal(String princode) {
		List<PA> list = rbacPA.listByPrincipal(princode);
		ACL acl = new ACL();
		for (PA pa : list) {
			Permission perm = rbacPermission.find(pa.getPermid());
			ACE e = new ACE(pa.getPrincode(), perm.getObjcode(), perm.getPermcode(), perm.getPermName());
			acl.addACE(e);
		}
		return acl;
	}

	@Override
	public ACL getACLByObject(String objcode) {
		List<PA> list = rbacPA.listByObject(objcode);
		ACL acl = new ACL();
		for (PA pa : list) {
			Permission perm = rbacPermission.find(pa.getPermid());
			ACE e = new ACE(pa.getPrincode(), perm.getObjcode(), perm.getPermcode(), perm.getPermName());
			acl.addACE(e);
		}
		return acl;
	}

	@Override
	public ACL getACL(String princode, String objcode) {
		List<PA> list = rbacPA.list(princode, objcode);
		ACL acl = new ACL();
		for (PA pa : list) {
			Permission perm = rbacPermission.find(pa.getPermid());
			ACE e = new ACE(pa.getPrincode(), perm.getObjcode(), perm.getPermcode(), perm.getPermName());
			acl.addACE(e);
		}
		return acl;
	}

	@Override
	public ACE getACE(String princode, String objcode, String permcode) {
		PA pa=rbacPA.find(princode,objcode, permcode);
		if(pa==null)return null;
		Permission perm = rbacPermission.find(pa.getPermid());
		return new ACE(pa.getPrincode(), perm.getObjcode(), perm.getPermcode(), perm.getPermName());
	}

	@Override
	public boolean hasRight(String princode, String objcode, String permcode) {
		return rbacPA.exists(princode,objcode, permcode);
	}

}
