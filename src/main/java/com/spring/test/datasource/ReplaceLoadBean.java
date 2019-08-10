package com.spring.test.datasource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class ReplaceLoadBean implements ApplicationContextAware, InitializingBean {

    private ConfigurableApplicationContext applicationContext;

    private XmlBeanDefinitionReader reader;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = (ConfigurableApplicationContext)applicationContext;
    }

    @Override
    public void afterPropertiesSet() {
         reader = new XmlBeanDefinitionReader((BeanDefinitionRegistry)applicationContext.getBeanFactory());
    }

    public void loadBeanDefinitions(ReplaceSource replaceSource){
        reader.loadBeanDefinitions(replaceSource);
    }

    public boolean containBean(String beanName){
        return applicationContext.containsBean(beanName);
    }


    public void removeBean(String beanName){
        if(containBean(beanName)){
            BeanDefinitionRegistry registry =  (BeanDefinitionRegistry)applicationContext.getBeanFactory();
            registry.removeBeanDefinition(beanName);
        }
    }

    // 资源定位、装载、注册
    public void test(){
        ClassPathResource resource = new ClassPathResource("bean.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);
    }
}
