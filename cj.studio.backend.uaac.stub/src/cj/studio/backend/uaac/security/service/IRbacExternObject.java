package cj.studio.backend.uaac.security.service;

import java.util.List;

import cj.studio.backend.uaac.security.ExternObjectBO;

/**
 * 获取外部资源。根据分类不同的获取策略
 * @author caroceanjofers
 *
 */
public interface IRbacExternObject {
	List<ExternObjectBO> objects(String category);
}
