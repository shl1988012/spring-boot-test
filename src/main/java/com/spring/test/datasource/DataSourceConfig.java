package com.spring.test.datasource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

@Configuration
public class DataSourceConfig implements DataSource, ApplicationContextAware {

    @Autowired
    private ReplaceLoadBean replaceLoadBean;

    private ApplicationContext applicationContext;

    private DataSource dataSource ;

    private String dataSourceName = "hsDataSourceName";

    @Override
    public Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void createDataSource(String dataSourceName){
        if(!replaceLoadBean.containBean(dataSourceName)){
            DataSourceReplaceBean dataSourceReplaceBean = new DataSourceReplaceBean();
            dataSourceReplaceBean.setBeanName(dataSourceName);
            dataSourceReplaceBean.setDriver("com.mysql.jdbc.Driver");
//            dataSourceReplaceBean.setUrl("jdbc:mysql://sjc-coi01-lnx:3306/ulm?useUnicode=true&characterEncoding=utf-8&useSSL=false");
            dataSourceReplaceBean.setUrl("jdbc:mysql://sjc-coi01-lnx:3306/ulm");
            dataSourceReplaceBean.setUserName("root");
            dataSourceReplaceBean.setPassword("password");

            replaceLoadBean.loadBeanDefinitions(new ReplaceSource(dataSourceReplaceBean));
        }
    }

    public DataSource getDataSource(){
        if(!this.applicationContext.containsBean(dataSourceName)){
            createDataSource(dataSourceName);
            return getDataSource(dataSourceName);
        }
        return  getDataSource(dataSourceName);
    }

    public DataSource getDataSource(String dataSourceName){
        return (DataSource) this.applicationContext.getBean(dataSourceName);
    }

}
