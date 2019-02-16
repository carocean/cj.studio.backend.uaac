package cj.studio.backend.uacc.security.service;

import java.util.List;

import cj.studio.backend.uacc.security.PA;

//角色/许可授权表包括角色标识、许可标识。系统管理员通过为角色分配或取消许可管理角色/许可授权表。
public interface IRbacPA {
	List<PA> listByPrincipal(String princode);

	List<PA> listByObject(String objcode);

	String add(PA bo);

	PA find(String princode, String permid);

	void remove(String paid);

	List<PA> list(String princode, String objcode);

	PA find(String princode, String objcode, String permcode);

	boolean exists(String princode, String objcode, String permcode);
}
