package cj.studio.backend.uacc.security;

public class Permission {
	 String id;
	String permcode;
	String objcode;//写法是：system://objcode/child
	String permName;
	public Permission() {
		// TODO Auto-generated constructor stub
	}
	public Permission(String id, String permcode, String objcode, String permName) {
		super();
		if (objcode.indexOf(".") < 0) {
			throw new RuntimeException("参数objcode格式不正确，未明确所在的受保护系统。格式为：system.objcode");
		}
		this.id = id;
		this.permcode = permcode;
		this.objcode = objcode;
		this.permName = permName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPermcode() {
		return permcode;
	}
	public void setPermcode(String permcode) {
		this.permcode = permcode;
	}
	public String getObjcode() {
		return objcode;
	}
	public void setObjcode(String objcode) {
		if (objcode.indexOf(".") < 0) {
			throw new RuntimeException("参数objcode格式不正确，未明确所在的受保护系统。格式为：system.objcode");
		}
		this.objcode = objcode;
	}
	public String getPermName() {
		return permName;
	}
	public void setPermName(String permName) {
		this.permName = permName;
	}
	
	
	
}
