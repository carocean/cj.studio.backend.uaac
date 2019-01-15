package cj.studio.backend.uaac.plugin.security;

import java.util.ArrayList;
import java.util.List;

import org.bson.BsonDocument;

import cj.lns.chip.sos.cube.framework.ICube;
import cj.lns.chip.sos.cube.framework.IDocument;
import cj.lns.chip.sos.cube.framework.IQuery;
import cj.lns.chip.sos.cube.framework.TupleDocument;
import cj.studio.backend.uaac.security.OperationBO;
import cj.studio.backend.uaac.security.service.IRbacOperation;
import cj.studio.ecm.EcmException;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
@CjService(name="rbacOperation")
public class RbacOperation implements IRbacOperation{
	@CjServiceRef(refByName = "mongodb.netos.uaac")
	ICube uaac;
	@Override
	public String add(OperationBO oper) {
		if(exists(oper.getCode())) {
			throw new EcmException(String.format("操作 %s 已存在", oper.getCode()));
		}
		return uaac.saveDoc("rbac.operation", new TupleDocument<OperationBO>(oper));
	}
	@Override
	public boolean exists(String code) {
		return uaac.tupleCount("rbac.operation", String.format("{'tuple.code':'%s'}", code))>0;
	}
	
	@Override
	public void remove(String id) {
		uaac.deleteDoc("rbac.operation", id);
	}
	@Override
	public OperationBO find(String id) {
		String cjql=String.format("select {'tuple':'*'} from tuple rbac.operation %s where {'_id':ObjectId('%s')}",OperationBO.class.getName(),id);
		IQuery<OperationBO> q=uaac.createQuery(cjql);
		IDocument<OperationBO> doc=q.getSingleResult();
		if(doc==null)return null;
		doc.tuple().setId(doc.docid());
		return doc.tuple();
	}
	@Override
	public void updateName(String id, String name) {
		String where=String.format("{'_id':ObjectId('%s')}", id);
		String update=String.format("{'$set':{'tuple.name':'%s'}}", name);
		
		uaac.updateDocOne("rbac.operation", BsonDocument.parse(where), BsonDocument.parse(update));
	}
	@Override
	public List<OperationBO> operations() {
		String cjql=String.format("select {'tuple':'*'} from tuple rbac.operation %s where {}",OperationBO.class.getName());
		IQuery<OperationBO> q=uaac.createQuery(cjql);
		List<OperationBO> operations=new ArrayList<>();
		List<IDocument<OperationBO>> list=q.getResultList();
		for(IDocument<OperationBO> tuple:list) {
			OperationBO u=tuple.tuple();
			u.setId(tuple.docid());
			operations.add(u);
		}
		return operations;
	}

}
