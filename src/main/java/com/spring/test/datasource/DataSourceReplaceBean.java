package com.spring.test.datasource;

import lombok.Data;

@Data
public class DataSourceReplaceBean {

    private String driver;

    private String url;

    private String userName;

    private String password;

    private String beanName;
    private String readOnly ="false";
    private String connectionTimeout ="30000";
    private String idleTimeout ="90000";
    private String maxLifetime ="1800000";
    private String maximumPoolSize = "15";

    public  String getBeanXml(){

        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
                .append("<beans xmlns=\"http://www.springframework.org/schema/beans\"")
                .append("       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"")
                .append("       xmlns:context=\"http://www.springframework.org/schema/context\"")
                .append("       xsi:schemaLocation=\"http://www.springframework.org/schema/beans")
                .append("                           http://www.springframework.org/schema/beans/spring-beans.xsd")
                .append("                           http://www.springframework.org/schema/context")
                .append("                           http://www.springframework.org/schema/context/spring-context.xsd\">");

        sb.append(" <bean id=\"" + beanName + "\" class=\"com.zaxxer.hikari.HikariDataSource\" primary=\"true\" destroy-method=\"close\" >")
                .append("    <property name=\"driverClassName\" value=\""+driver+"\"></property>")
                .append("    <property name=\"jdbcUrl\" value=\""+url+"\"></property>")
                .append("    <property name=\"username\" value=\""+userName+"\"></property>")
                .append("    <property name=\"password\" value=\""+password+"\"></property>")
                .append("    <property name=\"readOnly\" value=\""+readOnly+"\"></property>")
                .append("    <property name=\"connectionTimeout\" value=\""+connectionTimeout+"\"></property>")
                .append("    <property name=\"idleTimeout\" value=\""+idleTimeout+"\"></property>")
                .append("    <property name=\"maxLifetime\" value=\""+maxLifetime+"\"></property>")
                .append("    <property name=\"maximumPoolSize\" value=\""+maximumPoolSize+"\"></property>")
                .append("  </bean>");
        sb.append("</beans>");

        return sb.toString();
    }




}
