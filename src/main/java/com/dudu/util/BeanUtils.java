package com.dudu.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 获取实例bean工具类
 * @author huyuanzhi
 *
 */
public class BeanUtils implements ApplicationContextAware{

	
	private static ApplicationContext applicationContext; 
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		BeanUtils.applicationContext = applicationContext;
	}
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz)
	{
		return applicationContext.getBean(clazz);
	}
	

}
