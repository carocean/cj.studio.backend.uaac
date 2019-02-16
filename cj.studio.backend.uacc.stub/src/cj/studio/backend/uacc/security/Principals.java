package cj.studio.backend.uacc.security;

import java.util.HashMap;
import java.util.Map;

public class Principals {
	String code;
	String name;
	String desc;
	boolean isLeaf;//是否是叶
	String icon;
	Map<String, String> extra;
	public Principals() {
		extra=new HashMap<>();
	}
	public Principals(String code, String name,String icon, String desc, boolean isLeaf) {
		this();
		this.code = code;
		this.name = name;
		this.desc = desc;
		this.isLeaf = isLeaf;
		this.icon=icon;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public boolean isLeaf() {
		return isLeaf;
	}
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	public Map<String, String> getExtra() {
		return extra;
	}
	public void setExtra(Map<String, String> extra) {
		this.extra = extra;
	}
	
}
