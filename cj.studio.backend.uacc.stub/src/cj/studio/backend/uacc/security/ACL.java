package cj.studio.backend.uacc.security;

import java.util.ArrayList;

public class ACL extends ArrayList<ACE>{

	public ACL() {
	}

	public void addACE(ACE ace) {
		super.add(ace);
	}

	public void removeACE(ACE e) {
		super.remove(e);
	}

	public ACE get(int index) {
		return super.get(index);
	}

	public boolean isEmpty() {
		return super.isEmpty();
	}

	public int count() {
		return super.size();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (ACE e : this) {
			sb.append(String.format("%s\r\n", e.toString()));
		}
		if (sb.lastIndexOf("\r\n") > 0) {
			sb.delete(0, sb.length() - 2);
		}
		return sb.toString();
	}
}
