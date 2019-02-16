package cj.studio.backend.uacc.security.stub;

import java.util.ArrayList;
import java.util.List;

import cj.studio.backend.uacc.security.Permission;
import cj.studio.gateway.stub.annotation.CjStubInParameter;
import cj.studio.gateway.stub.annotation.CjStubMethod;
import cj.studio.gateway.stub.annotation.CjStubReturn;
import cj.studio.gateway.stub.annotation.CjStubService;

@CjStubService(bindService = "/permission.service", usage = "许可存根")
public interface IPermissionStub {
	@CjStubMethod(usage = "分配许可。将指定的对象关联到操作,可以为一个对象多次分配不同的操作")
	String assign(@CjStubInParameter(key = "objcode", usage = "对象代码") String objcode,
			@CjStubInParameter(key = "permcode", usage = "操作代码") String permcode,
			@CjStubInParameter(key = "permname", usage = "许可名") String permname);

	@CjStubMethod(usage = "将指定的操作从该对象关联中移除")
	void unassign(@CjStubInParameter(key = "objcode", usage = "对象代码") String objcode,
			@CjStubInParameter(key = "permcode", usage = "操作代码") String permcode);

	@CjStubMethod(usage = "将分配的许可移除")
	void remove(@CjStubInParameter(key = "permid", usage = "许可标识") String permid);

	@CjStubMethod(usage = "获取对象的所有许可")
	@CjStubReturn(type = ArrayList.class,elementType=Permission.class, usage = "许可集合")
	List<Permission> permissions(@CjStubInParameter(key = "objcode", usage = "对象代码") String objcode);

	@CjStubMethod(usage = "查找许可")
	@CjStubReturn(type = Permission.class, usage = "许可")
	Permission find(@CjStubInParameter(key = "objcode", usage = "对象代码") String objcode,
			@CjStubInParameter(key = "permcode", usage = "操作代码") String permcode);

	@CjStubMethod(usage = "查找许可")
	@CjStubReturn(type = Permission.class, usage = "许可")
	Permission findByPermissionId(@CjStubInParameter(key = "permid", usage = "许可标识") String permid);

	@CjStubMethod(usage = "是否存在许可")
	@CjStubReturn(type = Boolean.class, usage = "true是已存在")
	boolean exists(@CjStubInParameter(key = "objcode", usage = "对象代码") String objcode,
			@CjStubInParameter(key = "permcode", usage = "操作代码") String permcode);
}
