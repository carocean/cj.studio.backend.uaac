package cj.studio.backend.uaac.plugin.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cj.studio.backend.uaac.security.ExternObjectBO;
import cj.studio.backend.uaac.security.service.IObjectsFetchStrategy;
import cj.studio.backend.uaac.security.service.IRbacExternObject;
import cj.studio.ecm.CJSystem;
import cj.studio.ecm.EcmException;
import cj.studio.ecm.IServiceAfter;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.ServiceCollection;
import cj.studio.ecm.annotation.CjService;
import cj.ultimate.util.StringUtil;

@CjService(name = "rbacExternObject")
public class RbacExternObject implements IRbacExternObject, IServiceAfter {
	Map<String, IObjectsFetchStrategy> strategies;
	Map<String, String> categories;// key=category,value=categoryName

	@Override
	public List<ExternObjectBO> objects(String category) {
		if (!strategies.containsKey(category)) {
			throw new EcmException("资源分类策略不存在。" + category);
		}
		IObjectsFetchStrategy ofs = strategies.get(category);
		List<ExternObjectBO> list = ofs.objects();
		if(list==null) {
			throw new EcmException(String.format("资源策略%s 返回空的查询结果。%s", category, ofs));
		}
		for (ExternObjectBO bo : list) {
			if ( StringUtil.isEmpty(bo.getCode()) || StringUtil.isEmpty(bo.getName())) {
				throw new EcmException(String.format("资源分类%s数据获取失败，资源对象的一些信息项为空。%s", category, ofs));
			}
		}
		return list;
	}

	@Override
	public void onAfter(IServiceSite site) {
		strategies = new HashMap<>();
		categories = new HashMap<>();
		ServiceCollection<IObjectsFetchStrategy> col = site.getServices(IObjectsFetchStrategy.class);
		for (IObjectsFetchStrategy ofs : col) {
			if (StringUtil.isEmpty(ofs.getCategoryCode()) || StringUtil.isEmpty(ofs.getCategoryName())) {
				CJSystem.current().environment().logging().error(getClass(),
						String.format("无效的资源获取策略，其分类代码和分类名为空。%s", ofs));
				continue;
			}
			strategies.put(ofs.getCategoryCode(), ofs);
			categories.put(ofs.getCategoryCode(), ofs.getCategoryName());
			CJSystem.current().environment().logging().debug(getClass(),
					String.format("成功注册资源策略：%s %s %s",ofs.getCategoryCode(),ofs.getCategoryName(), ofs));
		}
	}

}
