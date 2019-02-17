package cj.studio.backend.uacc.security.stub;

import java.util.ArrayList;
import java.util.List;

import cj.studio.backend.uacc.security.ProtectedObject;
import cj.studio.backend.uacc.security.ProtectedObjectCollection;
import cj.studio.gateway.stub.annotation.CjStubInParameter;
import cj.studio.gateway.stub.annotation.CjStubMethod;
import cj.studio.gateway.stub.annotation.CjStubReturn;
import cj.studio.gateway.stub.annotation.CjStubService;

@CjStubService(bindService = "/protectedObject.service", usage = "受保护对象")
public interface IProtectedObjectStub {
	@CjStubMethod(usage = "获取子对象")
	@CjStubReturn(type = ArrayList.class,elementType=ProtectedObjectCollection.class, usage = "对象列表")
	List<ProtectedObject> getChildObjects(@CjStubInParameter(key = "sysCode", usage = "系统代码") String sysCode,
			@CjStubInParameter(key = "objColName", usage = "对象集合名") String objColName,
			@CjStubInParameter(key = "parentObjCode", usage = "父对象代码") String parentObjCode,
			@CjStubInParameter(key = "currPage", usage = "当前页") int currPage,
			@CjStubInParameter(key = "pageSize", usage = "页大小") int pageSize);

	@CjStubMethod(usage = "获取受保护对象集合定义")
	@CjStubReturn(type = ArrayList.class,elementType=ProtectedObjectCollection.class, usage = "对象集合列表")
	List<ProtectedObjectCollection> getAllProtectedObjectCollection(
			@CjStubInParameter(key = "sysCode", usage = "系统代码") String sysCode);// 提供界面显示有哪些对象表，然后点进去才是对象的实例

	@CjStubMethod(usage = "获取受保护对象集合")
	@CjStubReturn(type = ProtectedObjectCollection.class, usage = "对象集合")
	ProtectedObjectCollection getProtectedObjectCollection(
			@CjStubInParameter(key = "sysCode", usage = "系统代码") String sysCode,
			@CjStubInParameter(key = "objColName", usage = "对象集合名") String objColName);
}
