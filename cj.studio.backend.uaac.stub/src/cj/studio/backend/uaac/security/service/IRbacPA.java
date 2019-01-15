package cj.studio.backend.uaac.security.service;

import java.util.List;

import cj.studio.backend.uaac.security.PaBO;

//角色/许可授权表包括角色标识、许可标识。系统管理员通过为角色分配或取消许可管理角色/许可授权表。
public interface IRbacPA {
	boolean hasPermission(String roleid,String permid);
	String add(PaBO bo);
	void remove(String pa_id);
	void unassignPermission(String roleid,String permid);
	//清除所有许可上关联的所有角色
	void unassignAllPermissionByPermId(String permid);
	//清除所有角色上关联的所有许可
	void unassignAllPermissionByRole(String roleid);
	List<PaBO> pasByRoleId(String roleid);
	PaBO find(String rolecode, String permcode);
}
