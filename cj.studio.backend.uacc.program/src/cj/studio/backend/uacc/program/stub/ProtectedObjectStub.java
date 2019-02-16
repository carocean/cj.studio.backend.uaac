package cj.studio.backend.uacc.program.stub;

import java.util.List;

import cj.studio.backend.uacc.security.MicroServiceInfo;
import cj.studio.backend.uacc.security.ProtectedObject;
import cj.studio.backend.uacc.security.ProtectedObjectCollection;
import cj.studio.backend.uacc.security.ProtectedSystemInfo;
import cj.studio.backend.uacc.security.service.IProtectedSystemService;
import cj.studio.backend.uacc.security.stub.IProtectedObjectStub;
import cj.studio.ecm.EcmException;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.gateway.IRuntime;
import cj.studio.gateway.socket.Destination;
import cj.studio.gateway.stub.GatewayAppSiteRestStub;
import cj.studio.gateway.stub.IRest;

@CjService(name = "/protectedObject.service")
public class ProtectedObjectStub extends GatewayAppSiteRestStub implements IProtectedObjectStub {
	@CjServiceRef(refByName = "$.rest")
	IRest rest;
	@CjServiceRef(refByName = "$.gateway.runtime")
	IRuntime runtime;
	@CjServiceRef(refByName = "uaccplugin.protectedSystemService")
	IProtectedSystemService system;

	private IProtectedObjectStub getRemote(String sysCode) throws CircuitException {
		ProtectedSystemInfo info = system.getSystem(sysCode);
		if (info == null) {
			throw new EcmException("系统不存在：" + sysCode);
		}
		MicroServiceInfo micro = info.getPrincipalsMicroServiceInfo();

		// 从system中取得系统的主体微服务配置
		// 通过runtime建立网关的远程访问目标
		// 通过rest调用获取方法
		String name = micro.getName();
		if (name.startsWith("rest://")) {
			name = name.substring("rest:/".length(), name.length());
		}
		Destination dest = runtime.getDestination(name);
		if (dest != null) {
			runtime.removeDestination(name);
		}
		dest = setDestination(name, micro);
		runtime.addDestination(dest);
		IProtectedObjectStub ps;
		ps = rest.forRemote(dest.getName()).open(IProtectedObjectStub.class);
		return ps;
	}

	private Destination setDestination(String name, MicroServiceInfo micro) {
		Destination dest = new Destination(name);
		String uri = "";
		if (micro.getUri().lastIndexOf("?") > 0) {
			uri = String.format("%s&token=%s", micro.getUri(), micro.getToken());
		} else {
			uri = String.format("%s?token=%s", micro.getUri(), micro.getToken());
		}
		dest.getUris().add(uri);
		return dest;
	}

	@Override
	public List<ProtectedObject> getChildObjects(String sysCode, String objColName, String parentObjCode, int currPage,
			int pageSize) {
		try {
			IProtectedObjectStub ps = getRemote(sysCode);
			List<ProtectedObject> list = ps.getChildObjects(sysCode, objColName, parentObjCode, currPage, pageSize);
			return list;
		} catch (CircuitException e) {
			throw new EcmException(e);
		}
	}

	@Override
	public List<ProtectedObjectCollection> getAllProtectedObjectCollection(String sysCode) {
		try {
			IProtectedObjectStub ps = getRemote(sysCode);
			List<ProtectedObjectCollection> list = ps.getAllProtectedObjectCollection(sysCode);
			return list;
		} catch (CircuitException e) {
			throw new EcmException(e);
		}
	}

	@Override
	public ProtectedObjectCollection getProtectedObjectCollection(String sysCode, String objColName) {
		try {
			IProtectedObjectStub ps = getRemote(sysCode);
			ProtectedObjectCollection col = ps.getProtectedObjectCollection(sysCode, objColName);
			return col;
		} catch (CircuitException e) {
			throw new EcmException(e);
		}
	}

}
