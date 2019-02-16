package cj.studio.backend.uacc.security;

import java.util.HashMap;
import java.util.Map;

public class ProtectedObject {
	String code;
	String name;
	boolean isLeaf;// 是否是叶
	Map<String, Object> extra;
	public ProtectedObject() {
		extra=new HashMap<>();
	}
	public Map<String, Object> getExtra() {
		return extra;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
}
