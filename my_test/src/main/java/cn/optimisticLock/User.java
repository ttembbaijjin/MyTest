package cn.optimisticLock;

public class User {
	
	
	private String uid;
	
	private String uname;
	
	private int version;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", version=" + version + "]";
	}
	
	
	
}
