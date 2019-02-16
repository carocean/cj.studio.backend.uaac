package cj.studio.backend.uacc.program.stub;

import java.util.List;

import cj.studio.backend.uacc.security.Permission;
import cj.studio.backend.uacc.security.service.IRbacPermission;
import cj.studio.backend.uacc.security.stub.IPermissionStub;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.gateway.stub.GatewayAppSiteRestStub;
@CjService(name="/permission.service")
public class PermissionStub extends GatewayAppSiteRestStub implements IPermissionStub{
	@CjServiceRef(refByName="uaccplugin.rbacPermission")
	IRbacPermission rbacPermission;

	@Override
	public String assign(String objcode, String permcode, String permname) {
		return rbacPermission.assign(objcode, permcode, permname);
	}

	@Override
	public void unassign(String objcode, String permcode) {
		rbacPermission.unassign(objcode, permcode);
	}

	@Override
	public void remove(String permid) {
		rbacPermission.remove(permid);
	}

	@Override
	public List<Permission> permissions(String objcode) {
		return rbacPermission.permissions(objcode);
	}

	@Override
	public Permission find(String objcode, String permcode) {
		return rbacPermission.find(objcode, permcode);
	}

	@Override
	public Permission findByPermissionId(String permid) {
		return rbacPermission.find(permid);
	}

	@Override
	public boolean exists(String objcode, String permcode) {
		return rbacPermission.exists(objcode, permcode);
	}
	

}
