package com.spring.test.init;

import com.spring.test.springNewFeature.Condition.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class BeanFactory {

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverName;



    @Bean
    @Conditional(msgSenderConditionQQ.class)
    MsgSender initMsgSenderQQ(){
        System.out.println("111111111111111111111111111111111");
        return new MsgSenderQQ();
    }

    @Bean
    @Conditional(msgSenderConditionWECHAT.class)
    MsgSender initMsgSenderWechat(){
        System.out.println("22222222222222222222222222222222");
        return new MsgSenderWebchat();
    }


    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverName);
        dataSource.setUrl(dataSourceUrl);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);

        return dataSource;
    }


}
