package cj.studio.backend.uaac.plugin.security;

import java.util.ArrayList;
import java.util.List;

import org.bson.BsonDocument;

import cj.lns.chip.sos.cube.framework.ICube;
import cj.lns.chip.sos.cube.framework.IDocument;
import cj.lns.chip.sos.cube.framework.IQuery;
import cj.lns.chip.sos.cube.framework.TupleDocument;
import cj.studio.backend.uaac.security.RoleBO;
import cj.studio.backend.uaac.security.service.IRbacRole;
import cj.studio.ecm.EcmException;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
@CjService(name="rbacRole")
public class RbacRole implements IRbacRole{
	@CjServiceRef(refByName = "mongodb.netos.uaac")
	ICube uaac;

	@Override
	public String add(RoleBO role) {
		if(exists(role.getCode())) {
			throw new EcmException(String.format("角色 %s 已存在", role.getCode()));
		}
		return uaac.saveDoc("rbac.role", new TupleDocument<RoleBO>(role));
	}
	@Override
	public boolean exists(String code) {
		return uaac.tupleCount("rbac.role", String.format("{'tuple.code':'%s'}", code))>0;
	}
	@Override
	public void remove(String id) {
		uaac.deleteDoc("rbac.role", id);
	}

	@Override
	public RoleBO find(String rid) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateName(String id, String name) {
		String where=String.format("{'_id':ObjectId('%s')}", id);
		String update=String.format("{'$set':{'tuple.name':'%s'}}", name);
		
		uaac.updateDocOne("rbac.role", BsonDocument.parse(where), BsonDocument.parse(update));
	}
	@Override
	public void updateType(String id, String type) {
		String where=String.format("{'_id':ObjectId('%s')}", id);
		String update=String.format("{'$set':{'tuple.type':'%s'}}", type);
		
		uaac.updateDocOne("rbac.role", BsonDocument.parse(where), BsonDocument.parse(update));
	}
	@Override
	public List<RoleBO> roles() {
		String cjql=String.format("select {'tuple':'*'}.sort({'tuple.type':1,'tuple.code':1}) from tuple rbac.role %s where {}",RoleBO.class.getName());
		IQuery<RoleBO> q=uaac.createQuery(cjql);
		List<RoleBO> roles=new ArrayList<>();
		List<IDocument<RoleBO>> list=q.getResultList();
		for(IDocument<RoleBO> tuple:list) {
			RoleBO u=tuple.tuple();
			u.setId(tuple.docid());
			roles.add(u);
		}
		return roles;
	}

}
