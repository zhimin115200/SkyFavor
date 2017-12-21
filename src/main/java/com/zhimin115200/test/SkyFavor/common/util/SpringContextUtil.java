
package com.zhimin115200.test.SkyFavor.common.util;

import org.springframework.beans.BeansException;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class SpringContextUtil {

    // Spring应用上下文环境
    private static WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
    
    /**
     * 获取对象
     * 
     * @param name
     * @return Object
     * @throws BeansException
     */
    public static Object getBean(String name) throws BeansException {
        return wac.getBean(name);
    }

    /**
     * 获取对象
     * 
     * @param <T>
     * 
     * @param name
     * @return Object
     * @throws BeansException
     */
    public static <T> Object getBean(Class<T> requiredType)
            throws BeansException {
        return wac.getBean(requiredType);
    }

    /**
     * 获取对象
     * 
     * @param <T>
     * 
     * @param name
     * @return Object
     * @throws BeansException
     */
    public static <T> Object getBean(String name, Class<T> requiredType)
            throws BeansException {
        return wac.getBean(name, requiredType);
    }

}
