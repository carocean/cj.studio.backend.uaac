package cj.studio.backend.uacc.security.service;

import java.util.List;

import cj.studio.backend.uacc.security.ProtectedSystemInfo;

public interface IProtectedSystemService {

	void addSystem(ProtectedSystemInfo info);

	void updateSystem(ProtectedSystemInfo info);

	void removeSystem(String sysCode);

	List<ProtectedSystemInfo> getPage(int currPage, int pageSize);

	ProtectedSystemInfo getSystem(String sysCode);

}
