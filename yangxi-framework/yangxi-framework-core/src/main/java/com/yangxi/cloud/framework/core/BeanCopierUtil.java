package com.yangxi.cloud.framework.core;


import net.sf.cglib.beans.BeanCopier;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * BeanCopier工具类
 * </p>
 *
 * @author yangxi
 * @version 1.0
 */
public class BeanCopierUtil {

	/**
	 * BeanCopier缓存
	 */
	private static Map<String, BeanCopier> beanCopierCacheMap = new HashMap<>();

	/**
	 * 将source对象的属性拷贝到target对象中去
	 *
	 * @param source source对象
	 * @param target target对象
	 */
	public static void copyProperties(Object source, Object target) {
		String cacheKey = source.getClass().toString() + target.getClass().toString();

		BeanCopier beanCopier = null;

		if (!beanCopierCacheMap.containsKey(cacheKey)) {
			synchronized (BeanCopierUtil.class) {
				if (!beanCopierCacheMap.containsKey(cacheKey)) {
					beanCopier = BeanCopier.create(source.getClass(), target.getClass(), false);
					beanCopierCacheMap.put(cacheKey, beanCopier);
				} else {
					beanCopier = beanCopierCacheMap.get(cacheKey);
				}
			}
		} else {
			beanCopier = beanCopierCacheMap.get(cacheKey);
		}

		beanCopier.copy(source, target, null);
	}

}
