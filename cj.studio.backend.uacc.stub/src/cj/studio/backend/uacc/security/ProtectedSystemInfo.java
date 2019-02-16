package cj.studio.backend.uacc.security;

/*
 * 针对一个受保护系统只实现一套主体存根和对象存根，由于存根是调用者和实现者的约束，因此，当uacc会根据存根调用远程提供了主体或对象的微服务。故而此类只要知道远程微服务地址和访问令牌即可。
 */
public class ProtectedSystemInfo {
	String sysCode;
	String sysName;
	String desc;
	long cdate;
	MicroServiceInfo principalsMicroServiceInfo;
	MicroServiceInfo objectMicroServiceInfo;

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public long getCdate() {
		return cdate;
	}

	public void setCdate(long cdate) {
		this.cdate = cdate;
	}

	public MicroServiceInfo getPrincipalsMicroServiceInfo() {
		return principalsMicroServiceInfo;
	}

	public void setPrincipalsMicroServiceInfo(MicroServiceInfo principalsMicroServiceInfo) {
		this.principalsMicroServiceInfo = principalsMicroServiceInfo;
	}

	public MicroServiceInfo getObjectMicroServiceInfo() {
		return objectMicroServiceInfo;
	}

	public void setObjectMicroServiceInfo(MicroServiceInfo objectMicroServiceInfo) {
		this.objectMicroServiceInfo = objectMicroServiceInfo;
	}

}
