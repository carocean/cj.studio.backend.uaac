package cj.studio.backend.uaac.plugin.security;

import java.util.ArrayList;
import java.util.List;

import org.bson.BsonDocument;

import cj.lns.chip.sos.cube.framework.ICube;
import cj.lns.chip.sos.cube.framework.IDocument;
import cj.lns.chip.sos.cube.framework.IQuery;
import cj.lns.chip.sos.cube.framework.TupleDocument;
import cj.studio.backend.uaac.security.UserBO;
import cj.studio.backend.uaac.security.service.IRbacUser;
import cj.studio.ecm.EcmException;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
@CjService(name="rbacUser")
public class RbacUser implements IRbacUser {
	@CjServiceRef(refByName = "mongodb.netos.uaac")
	ICube uaac;

	@Override
	public String add(UserBO user) {
		if(exists(user.getCode())) {
			throw new EcmException(String.format("用户 %s 已存在", user.getCode()));
		}
		return uaac.saveDoc("rbac.user", new TupleDocument<UserBO>(user));
	}
	@Override
	public boolean exists(String code) {
		return uaac.tupleCount("rbac.user", String.format("{'tuple.code':'%s'}", code))>0;
	}
	@Override
	public boolean exists(String code,String pwd) {
		return uaac.tupleCount("rbac.user", String.format("{'tuple.code':'%s','tuple.password':'%s'}", code,pwd))>0;
	}
	@Override
	public void updateName(String id, String name) {
		String where=String.format("{'_id':ObjectId('%s')}", id);
		String update=String.format("{'$set':{'tuple.name':'%s'}}", name);
		
		uaac.updateDocOne("rbac.user", BsonDocument.parse(where), BsonDocument.parse(update));
	}
	@Override
	public void updatePassword(String id, String pwd) {
		String where=String.format("{'_id':ObjectId('%s')}", id);
		String update=String.format("{'$set':{'tuple.password':'%s'}}", pwd);
		
		uaac.updateDocOne("rbac.user", BsonDocument.parse(where), BsonDocument.parse(update));
		
	}
	@Override
	public void remove(String id) {
		uaac.deleteDoc("rbac.user", id);
	}

	@Override
	public UserBO find(String uid) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<UserBO> users() {
		String cjql=String.format("select {'tuple':'*'}.sort({'tuple.code':1}) from tuple rbac.user %s where {}",UserBO.class.getName());
		IQuery<UserBO> q=uaac.createQuery(cjql);
		List<UserBO> users=new ArrayList<>();
		List<IDocument<UserBO>> list=q.getResultList();
		for(IDocument<UserBO> tuple:list) {
			UserBO u=tuple.tuple();
			u.setId(tuple.docid());
			users.add(u);
		}
		return users;
	}
	@Override
	public List<UserBO> users(int startPage) {
		
		return null;
	}

}
