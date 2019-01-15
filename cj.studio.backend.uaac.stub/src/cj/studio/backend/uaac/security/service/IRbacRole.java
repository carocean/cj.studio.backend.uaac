package cj.studio.backend.uaac.security.service;
//包括角色标识、角色名称、角色基数、角色可用标识。角色表是系统角色集，由系统管理员定义角色。

import java.util.List;

import cj.studio.backend.uaac.security.RoleBO;

public interface IRbacRole {
	String add(RoleBO role);
	void remove(String id);
	RoleBO find(String rid);
	List<RoleBO> roles();
	boolean exists(String code);
	void updateName(String id,String name);
	void updateType(String id,String type);
}
