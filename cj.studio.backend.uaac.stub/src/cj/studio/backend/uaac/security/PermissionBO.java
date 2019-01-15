package cj.studio.backend.uaac.security;

public class PermissionBO {
	transient String id;
	String objid;
	String objcode;
	String operid;
	String opercode;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getObjid() {
		return objid;
	}
	public void setObjid(String objid) {
		this.objid = objid;
	}
	public String getObjcode() {
		return objcode;
	}
	public void setObjcode(String objcode) {
		this.objcode = objcode;
	}
	public String getOperid() {
		return operid;
	}
	public void setOperid(String operid) {
		this.operid = operid;
	}
	public String getOpercode() {
		return opercode;
	}
	public void setOpercode(String opercode) {
		this.opercode = opercode;
	}
	
}
