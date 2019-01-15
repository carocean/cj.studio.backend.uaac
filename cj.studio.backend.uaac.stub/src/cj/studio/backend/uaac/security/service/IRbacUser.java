package cj.studio.backend.uaac.security.service;
//包括用户标识、用户姓名、用户登录密码。用户表是系统中的个体用户集，随用户的添加与删除动态变化。

import java.util.List;

import cj.studio.backend.uaac.security.UserBO;

public interface IRbacUser {
	String add(UserBO user);
	void remove(String id);//物理id
	UserBO find(String uid);
	List<UserBO> users(int startPage);
	List<UserBO> users();
	boolean exists(String code);
	void updateName(String id,String name);
	void updatePassword(String id,String pwd);
	boolean exists(String code, String pwd);
}
