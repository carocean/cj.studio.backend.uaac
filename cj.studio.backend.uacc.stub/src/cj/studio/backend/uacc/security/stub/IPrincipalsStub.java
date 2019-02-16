package cj.studio.backend.uacc.security.stub;

import java.util.ArrayList;
import java.util.List;

import cj.studio.backend.uacc.security.PrincipalCollection;
import cj.studio.backend.uacc.security.Principals;
import cj.studio.gateway.stub.annotation.CjStubInParameter;
import cj.studio.gateway.stub.annotation.CjStubMethod;
import cj.studio.gateway.stub.annotation.CjStubReturn;
import cj.studio.gateway.stub.annotation.CjStubService;

@CjStubService(bindService = "/principals.service", usage = "主体。")
public interface IPrincipalsStub {
	@CjStubMethod(usage = "获取子主体")
	@CjStubReturn(type = ArrayList.class, usage = "主体列表")
	List<Principals> getChildPrincipals(@CjStubInParameter(key = "sysCode", usage = "系统代码") String sysCode,
			@CjStubInParameter(key = "principalColName", usage = "主体集合名") String principalColName,
			@CjStubInParameter(key = "parentPrincipalCode", usage = "父主体代码") String parentPrincipalCode,
			@CjStubInParameter(key = "currPage", usage = "当前页") int currPage,
			@CjStubInParameter(key = "pageSize", usage = "页大小") int pageSize);

	@CjStubMethod(usage = "获取关联主体指定代码包含的相关主体列表")
	@CjStubReturn(type = ArrayList.class,elementType=Principals.class, usage = "主体列表")
	List<Principals> getContainsPrincipals(@CjStubInParameter(key = "sysCode", usage = "系统代码") String sysCode,
			@CjStubInParameter(key = "principalColName", usage = "主体集合名") String principalColName,
			@CjStubInParameter(key = "relactionshipPrincipalColName", usage = "关系主体表名") String relactionshipPrincipalColName,
			@CjStubInParameter(key = "principalCode", usage = "主体代码") String principalCode,
			@CjStubInParameter(key = "currPage", usage = "当前页") int currPage,
			@CjStubInParameter(key = "pageSize", usage = "页大小") int pageSize);

	@CjStubMethod(usage = "获取所有主体表定义")
	@CjStubReturn(type = ArrayList.class,elementType=PrincipalCollection.class, usage = "主体表列表")
	List<PrincipalCollection> getAllPrincipalCollections(
			@CjStubInParameter(key = "sysCode", usage = "系统代码") String sysCode);// 提供界面显示有哪些主体表，然后点进去才是主体的实例

	@CjStubMethod(usage = "获取指定主体表的定义")
	@CjStubReturn(type = ArrayList.class,elementType=PrincipalCollection.class, usage = "主体表")
	PrincipalCollection getPrincipalCollection(@CjStubInParameter(key = "sysCode", usage = "系统代码") String sysCode,
			@CjStubInParameter(key = "principalColName", usage = "主体集合名") String principalColName);// 主要用于列出指定主体的关系主体

}
