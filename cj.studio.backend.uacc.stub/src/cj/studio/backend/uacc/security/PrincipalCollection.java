package cj.studio.backend.uacc.security;

import java.util.ArrayList;
import java.util.List;

//定义主体获取的远程服务信息及主体间关系，如用户和角色、部门和用户、部门父子关系等
public class PrincipalCollection {
	String colName;//主体集合名,在同一系统中唯一
	String colTitle;
	String sysCode;
	List<Relactionship> relactionships;//一对多，如用户主体就有与角色、部门、岗位有多个关系。
	public PrincipalCollection() {
		this.relactionships=new ArrayList<>();
	}
	public String getColTitle() {
		return colTitle;
	}
	public void setColTitle(String colTitle) {
		this.colTitle = colTitle;
	}
	public String getColName() {
		return colName;
	}
	public void setColName(String colName) {
		this.colName = colName;
	}
	public String getSysCode() {
		return sysCode;
	}
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
	public List<Relactionship> getRelactionships() {
		return relactionships;
	}
	
}
