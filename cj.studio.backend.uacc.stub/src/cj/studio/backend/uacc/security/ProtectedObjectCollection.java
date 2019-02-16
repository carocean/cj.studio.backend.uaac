package cj.studio.backend.uacc.security;

//定义对象的远程获取服务信息，如：页面与其元素、菜单与子菜单等
public class ProtectedObjectCollection {
	String colName;//主体集合名,在同一系统中唯一
	String colTitle;
	String sysCode;
	public ProtectedObjectCollection() {
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
}
