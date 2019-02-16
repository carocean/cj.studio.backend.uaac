package cj.studio.backend.uacc.security.service;
//包括许可标识、许可名称、受控对象、操作标识。许可表给出了受控对象与操作算子的对应关系。
//RBAC中许可被授权给角色，角色被授权给用户，用户不直接与许可关联。

import java.util.List;

import cj.studio.backend.uacc.security.Permission;

public interface IRbacPermission {
	String assign(String objcode,String permcode,String permname);
	void unassign(String objcode,String permcode);
	void remove(String permid);
	List<Permission> permissions(String objcode);
	Permission find(String objcode, String permcode);
	Permission find(String permid);
	boolean exists(String objcode, String permcode);
}
