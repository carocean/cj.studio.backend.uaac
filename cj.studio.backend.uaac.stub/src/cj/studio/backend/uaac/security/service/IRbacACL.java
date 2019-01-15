package cj.studio.backend.uaac.security.service;
//ACL直接将主体和受控客体相联系，而RBAC在中间加入了角色，通过角色沟通主体与客体。分层的优点是当主体发生变化时，只需修改主体与角色之间的关联而不必修改角色与客体的关联。
//查询访问控制列表，是rbac的外观接口

import java.util.List;

import cj.studio.backend.uaac.security.PermissionBO;

public interface IRbacACL {
	List<PermissionBO> getUserPermssions(String uid);
	List<PermissionBO> getUserPermssionsInObject(String uid,String oid);
	List<PermissionBO> getRolePermssions(String roleid,String oid);
	boolean hasRole(String uid,String roleid);
	
}
