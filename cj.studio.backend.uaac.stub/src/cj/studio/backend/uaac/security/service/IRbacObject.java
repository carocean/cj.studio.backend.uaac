package cj.studio.backend.uaac.security.service;
//包括对象标识、对象名称。客体表是系统中所有受控对象的集合。

import java.util.List;

import cj.studio.backend.uaac.security.ObjectBO;

public interface IRbacObject {
	String add(ObjectBO obj);
	void remove(String oid);
	ObjectBO find(String oid);
	List<ObjectBO> objects();
	boolean exists(String code);
	void updateName(String id, String name);
	ObjectBO findOne(String cate,String code);
}
