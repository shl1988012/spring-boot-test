package com.spring.test.datasource;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
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
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Configuration
public class DataSourceConfig implements DataSource, ApplicationContextAware {

    @Autowired
    private ReplaceLoadBean replaceLoadBean;

    private ApplicationContext applicationContext;

    private DataSource dataSource ;

    private String dataSourceName = "hsDataSourceName";

    private ThreadPoolExecutor executor =new ThreadPoolExecutor(1,2,5,TimeUnit.SECONDS,
             new LinkedBlockingDeque<>(), new ThreadFactoryBuilder().setNameFormat("ds-refresh-thread-%d").build());

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
            dataSourceReplaceBean.setDriver("com.mysql.cj.jdbc.Driver");
            dataSourceReplaceBean.setUrl("jdbc:mysql://172.31.129.156:3306/shltest?useSSL=false");
//            dataSourceReplaceBean.setUrl("jdbc:mysql://sjc-coi01-lnx:3306/ulm");
            dataSourceReplaceBean.setUserName("root");
            dataSourceReplaceBean.setPassword("root");

            replaceLoadBean.loadBeanDefinitions(new ReplaceSource(dataSourceReplaceBean));
        }
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource(){
        if(!this.applicationContext.containsBean(dataSourceName)){
            createDataSource(dataSourceName);
            setDataSource( getDataSource(dataSourceName));
        }
        return  dataSource;
    }


    public DataSource getDataSource(String dataSourceName){
        return (DataSource) this.applicationContext.getBean(dataSourceName);
    }

}
