package cj.studio.backend.uacc.plugin.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import cj.lns.chip.sos.cube.framework.ICube;
import cj.lns.chip.sos.cube.framework.IDocument;
import cj.lns.chip.sos.cube.framework.IQuery;
import cj.lns.chip.sos.cube.framework.TupleDocument;
import cj.studio.backend.uacc.security.ProtectedSystemInfo;
import cj.studio.backend.uacc.security.service.IProtectedSystemService;
import cj.studio.ecm.EcmException;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.ultimate.gson2.com.google.gson.Gson;

@CjService(name = "protectedSystemService")
public class ProtectedSystemService implements IProtectedSystemService {
	@CjServiceRef(refByName = "mongodb.netos.uacc")
	ICube uacc;

	@Override
	public void addSystem(ProtectedSystemInfo info) {
		String cjql = "select {'tuple':'*'}.count() from tuple system ?(clazz) where {'tuple.sysCode':'?(sysCode)'}";
		IQuery<ProtectedSystemInfo> q = uacc.createQuery(cjql);
		q.setParameter("clazz", ProtectedSystemInfo.class.getName());
		q.setParameter("sysCode", info.getSysCode());
		if (q.count() > 0) {
			throw new EcmException("已存在受保护系统：" + info.getSysCode());
		}
		uacc.saveDoc("system", new TupleDocument<>(info));
	}

	@Override
	public void updateSystem(ProtectedSystemInfo info) {
		Bson where=Document.parse(String.format("{'tuple.sysCode':'%s'}",info.getSysCode()));
		Bson set=Document.parse(String.format("{'$set':{'tuple':%s}}", new Gson().toJson(info)));
		uacc.updateDocOne("system", where, set);

	}

	@Override
	public void removeSystem(String sysCode) {
		uacc.deleteDocOne("system", String.format("{'tuple.sysCode':'%s'}", sysCode));
	}

	@Override
	public List<ProtectedSystemInfo> getPage(int currPage, int pageSize) {
		String cjql = "select {'tuple':'*'}.skip(?(currPage)).limit(?(pageSize)) from tuple system ?(clazz) where {}";
		IQuery<ProtectedSystemInfo> q = uacc.createQuery(cjql);
		q.setParameter("clazz", ProtectedSystemInfo.class.getName());
		q.setParameter("currPage", currPage);
		q.setParameter("pageSize", pageSize);
		List<ProtectedSystemInfo> list=new ArrayList<>();
		List<IDocument<ProtectedSystemInfo>> relist=q.getResultList();
		for(IDocument<ProtectedSystemInfo> doc:relist) {
			list.add(doc.tuple());
		}
		return list;
	}

	@Override
	public ProtectedSystemInfo getSystem(String sysCode) {
		String cjql = "select {'tuple':'*'} from tuple system ?(clazz) where {'tuple.sysCode':'?(sysCode)'}";
		IQuery<ProtectedSystemInfo> q = uacc.createQuery(cjql);
		q.setParameter("clazz", ProtectedSystemInfo.class.getName());
		q.setParameter("sysCode", sysCode);
		IDocument<ProtectedSystemInfo> doc = q.getSingleResult();
		if (doc == null)
			return null;
		return doc.tuple();
	}

}
