package cn.ycc.api.admin.commons.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author yuchaoqun
 * @email chaoqunyu9311@163.com
 * @date 2020.11.24 21:32
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    private  static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.context = applicationContext;
    }

    public static String[] getBeanDefinitionNames(){
        return context.getBeanDefinitionNames();
    }
    public static Object getBean(String beanName){
        return context.getBean(beanName);
    }

    public static <T>T getBean(String beanName,Class<T> type){
        return context.getBean(beanName,type);
    }

    public static <T> T getBean(Class<T> type){
        return context.getBean(type);
    }
}
