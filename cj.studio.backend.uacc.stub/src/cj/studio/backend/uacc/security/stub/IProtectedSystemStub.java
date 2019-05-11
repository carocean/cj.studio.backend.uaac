package cj.studio.backend.uacc.security.stub;

import java.util.ArrayList;
import java.util.List;

import cj.studio.backend.uacc.security.ProtectedSystemInfo;
import cj.studio.gateway.stub.annotation.CjStubInContentKey;
import cj.studio.gateway.stub.annotation.CjStubInParameter;
import cj.studio.gateway.stub.annotation.CjStubMethod;
import cj.studio.gateway.stub.annotation.CjStubReturn;
import cj.studio.gateway.stub.annotation.CjStubService;

@CjStubService(bindService = "/protectedSystem.service", usage = "受保护系统定义")
public interface IProtectedSystemStub {
	@CjStubMethod(usage = "添加受保护系统", command = "post")
	void addSystem(@CjStubInContentKey(key = "info", usage = "受保护系统信息") ProtectedSystemInfo info);
	@CjStubMethod(usage = "更新受保护系统", command = "post")
	void updateSystem(@CjStubInContentKey(key = "info", usage = "受保护系统信息") ProtectedSystemInfo info);
	@CjStubMethod(usage = "移除受保护系统")
	void removeSystem(@CjStubInParameter(key = "sysCode", usage = "系统代码") String sysCode);

	@CjStubMethod(usage = "获取一页")
	@CjStubReturn(type=ArrayList.class,elementType=ProtectedSystemInfo.class,usage="返回一页数据")
	List<ProtectedSystemInfo> getPage(@CjStubInParameter(key = "currPage", usage = "系统代码") int currPage,
			@CjStubInParameter(key = "pageSize", usage = "系统代码") int pageSize);
	@CjStubMethod(usage = "获取一个系统信息")
	@CjStubReturn(type=ProtectedSystemInfo.class,usage="如果不存在则返回空")
	ProtectedSystemInfo getSystem(@CjStubInParameter(key = "sysCode", usage = "系统代码")String sysCode);

}
