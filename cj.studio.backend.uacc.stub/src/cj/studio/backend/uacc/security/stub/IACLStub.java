package cj.studio.backend.uacc.security.stub;

import cj.studio.backend.uacc.security.ACE;
import cj.studio.backend.uacc.security.ACL;
import cj.studio.gateway.stub.annotation.CjStubInParameter;
import cj.studio.gateway.stub.annotation.CjStubMethod;
import cj.studio.gateway.stub.annotation.CjStubReturn;
import cj.studio.gateway.stub.annotation.CjStubService;

@CjStubService(bindService = "/acl.service", usage = "访问控制列表存根")
public interface IACLStub {
	@CjStubMethod(usage = "获取指定主体代码对应的访问控制列表")
	@CjStubReturn(type = ACL.class, usage = "控制列表")
	ACL getACLByPrincipal(@CjStubInParameter(key = "princode", usage = "主体代码") String princode);

	@CjStubMethod(usage = "获取指定对象代码对应的访问控制列表")
	@CjStubReturn(type = ACL.class, usage = "控制列表")
	ACL getACLByObject(@CjStubInParameter(key = "objcode", usage = "对象代码") String objcode);

	@CjStubMethod(usage = "获取指定主体和对象对应的访问控制列表")
	@CjStubReturn(type = ACL.class, usage = "控制列表")
	ACL getACL(@CjStubInParameter(key = "princode", usage = "主体代码") String princode,
			@CjStubInParameter(key = "objcode", usage = "对象代码") String objcode);

	@CjStubMethod(usage = "获取指定主体和对象对应的访问控制项")
	@CjStubReturn(type = ACE.class, usage = "控制项，如果没有返回null")
	ACE getACE(@CjStubInParameter(key = "princode", usage = "主体代码") String princode,
			@CjStubInParameter(key = "objcode", usage = "对象代码") String objcode,
			@CjStubInParameter(key = "permcode", usage = "许可代码") String permcode);

	@CjStubMethod(usage = "判断指定主体和对象是否已分配指定的许可权限")
	@CjStubReturn(type = Boolean.class, usage = "true表示主体对对象具有该许可权限")
	boolean hasRight(@CjStubInParameter(key = "princode", usage = "主体代码") String princode,
			@CjStubInParameter(key = "objcode", usage = "对象代码") String objcode,
			@CjStubInParameter(key = "permcode", usage = "许可代码") String permcode);
}
