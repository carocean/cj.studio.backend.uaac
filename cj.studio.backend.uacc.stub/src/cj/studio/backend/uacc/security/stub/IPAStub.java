package cj.studio.backend.uacc.security.stub;

import java.util.ArrayList;
import java.util.List;

import cj.studio.backend.uacc.security.PA;
import cj.studio.gateway.stub.annotation.CjStubInContentKey;
import cj.studio.gateway.stub.annotation.CjStubInParameter;
import cj.studio.gateway.stub.annotation.CjStubMethod;
import cj.studio.gateway.stub.annotation.CjStubReturn;
import cj.studio.gateway.stub.annotation.CjStubService;

@CjStubService(bindService = "/pa.service", usage = "pa存根，用于为主体分配许可")
public interface IPAStub {
	@CjStubMethod(usage = "以指定的主体代码列出pa")
	@CjStubReturn(type=ArrayList.class,elementType=PA.class,usage="PA集合")
	List<PA> listByPrincipal(@CjStubInParameter(key = "princode", usage = "主体代码") String princode);

	@CjStubMethod(usage = "以指定的对象代码列出pa")
	@CjStubReturn(type=ArrayList.class,elementType=PA.class,usage="PA集合")
	List<PA> listByObject(@CjStubInParameter(key = "objcode", usage = "对象代码") String objcode);

	@CjStubMethod(usage = "以指定的对象代码列出pa", command = "post")
	@CjStubReturn(type = String.class, usage = "返回paid")
	String add(@CjStubInContentKey(key = "pa", usage = "pa实体") PA pa);

	@CjStubMethod(usage = "查找pa")
	PA find(@CjStubInParameter(key = "princode", usage = "实体代码") String princode,
			@CjStubInParameter(key = "permid", usage = "许可标识") String permid);

	@CjStubMethod(usage = "移除pa")
	void remove(@CjStubInParameter(key = "paid", usage = "pa标识") String paid);
}
