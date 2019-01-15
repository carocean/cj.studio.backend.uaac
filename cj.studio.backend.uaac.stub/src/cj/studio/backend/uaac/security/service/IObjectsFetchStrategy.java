package cj.studio.backend.uaac.security.service;

import java.util.List;

import cj.studio.backend.uaac.security.ExternObjectBO;
import cj.studio.ecm.annotation.CjExotericalType;

/**
 * 资源获取策略,可在各模块芯片中定义策略，系统自动注册
 * @author caroceanjofers
 *
 */
@CjExotericalType
public interface IObjectsFetchStrategy {

	String getCategoryCode();
	String getCategoryName();
//策略：分类编号、分类名、获取对象的方法实现

	List<ExternObjectBO> objects();
}
