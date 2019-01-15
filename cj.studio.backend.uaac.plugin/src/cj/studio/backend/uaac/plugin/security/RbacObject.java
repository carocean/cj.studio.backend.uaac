package cj.studio.backend.uaac.plugin.security;

import java.util.ArrayList;
import java.util.List;

import org.bson.BsonDocument;

import cj.lns.chip.sos.cube.framework.ICube;
import cj.lns.chip.sos.cube.framework.IDocument;
import cj.lns.chip.sos.cube.framework.IQuery;
import cj.lns.chip.sos.cube.framework.TupleDocument;
import cj.studio.backend.uaac.security.ObjectBO;
import cj.studio.backend.uaac.security.service.IRbacObject;
import cj.studio.ecm.EcmException;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.ultimate.util.StringUtil;

@CjService(name = "rbacObject")
public class RbacObject implements IRbacObject {
	@CjServiceRef(refByName = "mongodb.netos.uaac")
	ICube uaac;

	@Override
	public String add(ObjectBO obj) {
		if (exists(obj.getCode())) {
			throw new EcmException(String.format("资源 %s 已存在", obj.getCode()));
		}
		if (StringUtil.isEmpty(obj.getSource())) {
			obj.setSource(".");// 如果为.号表示自定义的许可，即无来源
		}
		return uaac.saveDoc("rbac.object", new TupleDocument<ObjectBO>(obj));
	}

	@Override
	public boolean exists(String code) {
		return uaac.tupleCount("rbac.object", String.format("{'tuple.code':'%s'}", code)) > 0;
	}

	@Override
	public void remove(String id) {
		uaac.deleteDoc("rbac.object", id);
	}

	@Override
	public ObjectBO findOne(String cate, String code) {
		String cjql = String.format(
				"select {'tuple':'*'} from tuple rbac.object %s where {'tuple.code':'%s','tuple.source':'%s'}",
				ObjectBO.class.getName(), code, cate);
		IQuery<ObjectBO> q = uaac.createQuery(cjql);
		IDocument<ObjectBO> doc = q.getSingleResult();
		if (doc == null)
			return null;
		ObjectBO bo = doc.tuple();
		bo.setId(doc.docid());
		return bo;
	}

	@Override
	public ObjectBO find(String oid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateName(String id, String name) {
		String where = String.format("{'_id':ObjectId('%s')}", id);
		String update = String.format("{'$set':{'tuple.name':'%s'}}", name);

		uaac.updateDocOne("rbac.object", BsonDocument.parse(where), BsonDocument.parse(update));
	}

	@Override
	public List<ObjectBO> objects() {
		String cjql = String.format("select {'tuple':'*'}.sort({'tuple.code':1}) from tuple rbac.object %s where {}",
				ObjectBO.class.getName());
		IQuery<ObjectBO> q = uaac.createQuery(cjql);
		List<ObjectBO> objs = new ArrayList<>();
		List<IDocument<ObjectBO>> list = q.getResultList();
		for (IDocument<ObjectBO> tuple : list) {
			ObjectBO u = tuple.tuple();
			u.setId(tuple.docid());
			objs.add(u);
		}
		return objs;
	}

}
