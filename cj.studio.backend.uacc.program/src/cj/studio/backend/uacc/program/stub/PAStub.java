package cj.studio.backend.uacc.program.stub;

import java.util.List;

import cj.studio.backend.uacc.security.PA;
import cj.studio.backend.uacc.security.service.IRbacPA;
import cj.studio.backend.uacc.security.stub.IPAStub;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.gateway.stub.GatewayAppSiteRestStub;

@CjService(name = "/pa.service")
public class PAStub extends GatewayAppSiteRestStub implements IPAStub {
	@CjServiceRef(refByName = "uaccplugin.rbacPA")
	IRbacPA rbacPA;

	@Override
	public List<PA> listByPrincipal(String princode) {
		return rbacPA.listByPrincipal(princode);
	}

	@Override
	public List<PA> listByObject(String objcode) {
		return rbacPA.listByObject(objcode);
	}

	@Override
	public String add(PA pa) {
		return rbacPA.add(pa);
	}

	@Override
	public PA find(String princode, String permid) {
		return rbacPA.find(princode, permid);
	}

	@Override
	public void remove(String paid) {
		rbacPA.remove(paid);
	}

}
