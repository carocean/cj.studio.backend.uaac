package cj.studio.backend.uaac.security.service;
//包括许可标识、许可名称、受控对象、操作标识。许可表给出了受控对象与操作算子的对应关系。
//RBAC中许可被授权给角色，角色被授权给用户，用户不直接与许可关联。

import java.util.List;

import cj.studio.backend.uaac.security.PermissionBO;

public interface IRbacPermission {
	//为受控对象分配操作以生成许可，如果已为受控对象分配了操作，则报错
	String assign(String oid,String ocode,String opid,String opcode)throws Exception;
	void unassign(String oid,String opid);
	void remove(String pid);
	List<PermissionBO> permissions(String oid);
	List<PermissionBO> permissions();
	PermissionBO find(String pid);
	boolean exists(String ocode, String opcode);
	PermissionBO find(String ocode, String opcode);
}
