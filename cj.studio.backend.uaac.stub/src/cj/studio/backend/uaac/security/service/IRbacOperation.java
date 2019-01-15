package cj.studio.backend.uaac.security.service;
//包括操作标识、操作算子名称。系统中所有受控对象的操作算子构成操作算子表

import java.util.List;

import cj.studio.backend.uaac.security.OperationBO;

public interface IRbacOperation {
	String add(OperationBO opid);
	void remove(String opid);
	List<OperationBO> operations();
	boolean exists(String code);
	void updateName(String id, String name);
	OperationBO find(String id);
}
