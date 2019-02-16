package cj.studio.backend.uacc.program.stub;

import java.util.List;

import cj.studio.backend.uacc.security.ProtectedSystemInfo;
import cj.studio.backend.uacc.security.service.IProtectedSystemService;
import cj.studio.backend.uacc.security.stub.IProtectedSystemStub;
import cj.studio.ecm.EcmException;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.gateway.stub.GatewayAppSiteRestStub;
import cj.ultimate.util.StringUtil;
@CjService(name="/protectedSystem.service")
public class ProtectedSystemStub extends GatewayAppSiteRestStub implements IProtectedSystemStub {
	@CjServiceRef(refByName="uaccplugin.protectedSystemService")
	IProtectedSystemService system;
	@Override
	public void addSystem(ProtectedSystemInfo info) {
		if(info.getCdate()==0) {
			info.setCdate(System.currentTimeMillis());
		}
		if(StringUtil.isEmpty(info.getSysCode())) {
			throw new EcmException("受保护系统代码为空");
		}
		if(StringUtil.isEmpty(info.getSysName())) {
			throw new EcmException("受保护系统名为空");
		}
		system.addSystem(info);
	}
	@Override
	public void updateSystem(ProtectedSystemInfo info) {
		system.updateSystem(info);
		
	}
	@Override
	public void removeSystem(String sysCode) {
		system.removeSystem(sysCode);
	}

	@Override
	public List<ProtectedSystemInfo> getPage(int currPage, int pageSize) {
		return system.getPage(currPage,pageSize);
	}

	@Override
	public ProtectedSystemInfo getSystem(String sysCode) {
		return system.getSystem(sysCode);
	}

}
