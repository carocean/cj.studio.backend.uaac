package cj.studio.backend.uacc.security;

public class ACE {
	String prinCode;
	String objcode;
	String permcode;
	String permName;

	public ACE() {
		// TODO Auto-generated constructor stub
	}

	public ACE(String prinCode, String objcode, String permcode, String permName) {
		this.permcode = permcode;
		this.permName = permName;
		this.prinCode = prinCode;
		this.objcode = objcode;
	}

	public String getPrinCode() {
		return prinCode;
	}

	public void setPrinCode(String prinCode) {
		this.prinCode = prinCode;
	}

	public String getObjcode() {
		return objcode;
	}

	public void setObjcode(String objcode) {
		this.objcode = objcode;
	}

	public String getPermcode() {
		return permcode;
	}

	public void setPermcode(String permcode) {
		this.permcode = permcode;
	}

	public String getPermName() {
		return permName;
	}

	public void setPermName(String permName) {
		this.permName = permName;
	}

	@Override
	public String toString() {
		return String.format("%s %s %s(%s)", prinCode, objcode, permcode, permName);
	}
}
