package com.spring.test.datasource;

import lombok.Data;

@Data
public class DataSourceReplaceBean {

    private String driver;

    private String url;

    private String userName;

    private String password;


    private String beanName;
    private String readOnly;
    private String connectionTimeout;
    private String idleTimeout;
    private String maxLifetime;
    private String maximumPoolSize;

    public  String getBeanXml(){

        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
                .append("<beans xmlns=\"http://www.springframework.org/schema/beans\"")
                .append("    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"")
                .append("    xmlns:context=\"http://www.springframework.org/schema/context\"")
                .append("    xsi:schemaLocation=\"")
                .append("    http://www.springframework.org/schema/beans")
                .append("    http://www.springframework.org/schema/beans/spring-beans-3.0.xsd")
                .append("    http://www.springframework.org/schema/context")
                .append("    http://www.springframework.org/schema/context/spring-context-3.0.xsd\">");

        sb.append(" <bean id=\"" + beanName + "\" class=\"com.zaxxer.hikari.HikariDataSource\" primary=\"true\" destroy-method=\"close\"")
                .append("    <property name=\"driverClassName\" value=\""+driver+"\"></property>")
                .append("    <property name=\"jdbcUrl\" value=\""+url+"\"></property>")
                .append("    <property name=\"username\" value=\""+userName+"\"></property>")
                .append("    <property name=\"password\" value=\""+password+"\"></property>")
                .append("    <property name=\"readOnly\" value=\"false\" />")
                .append("    <property name=\"connectionTimeout\" value=\""+connectionTimeout+"\" />")
                .append("    <property name=\"idleTimeout\" value=\""+idleTimeout+"\" />")
                .append("    <property name=\"maxLifetime\" value=\""+maxLifetime+"\" />")
                .append("    <property name=\"maximumPoolSize\" value=\""+maximumPoolSize+"\" />")
                .append("  </bean>");
        sb.append("</beans>");

        return sb.toString();
    }




}
