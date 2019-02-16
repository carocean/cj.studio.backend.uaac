package cj.studio.backend.uacc.security;

public class PA {
	 String id;
	String princode;// 写法是：system://code.childcode
	String permid;

	public PA() {
		// TODO Auto-generated constructor stub
	}

	public PA(String id, String prinCode, String permid) {
		super();
		if (prinCode.indexOf(".") < 0) {
			throw new RuntimeException("参数prinCode格式不正确，未明确所在的受保护系统。格式为：system.prinCode");
		}
		this.id = id;
		this.princode = prinCode;
		this.permid = permid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrincode() {
		return princode;
	}

	public void setPrincode(String prinCode) {
		if (prinCode.indexOf(".") < 0) {
			throw new RuntimeException("参数prinCode格式不正确，未明确所在的受保护系统。格式为：system.prinCode");
		}
		this.princode = prinCode;
	}

	public String getPermid() {
		return permid;
	}

	public void setPermid(String permid) {
		this.permid = permid;
	}

}
