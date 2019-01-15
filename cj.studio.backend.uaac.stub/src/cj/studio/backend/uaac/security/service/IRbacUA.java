package cj.studio.backend.uaac.security.service;

import java.util.List;

import cj.studio.backend.uaac.security.UaBO;

//用户角色分配
public interface IRbacUA {
	boolean hasRole(String uid,String roleid);
	String add(UaBO bo);
	void remove(String ua_id);
	void unassignAllByUser(String uid);
	void unassignAllByRole(String rid);
	List<UaBO> pasByRoleId(String roleid);
	List<UaBO> pasByUserId(String uid);
	UaBO find(String rcode, String ucode);
	List<String> findUCodesByRCode(String rcode);
}
