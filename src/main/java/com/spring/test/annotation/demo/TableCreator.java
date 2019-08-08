package com.spring.test.annotation.demo;


import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 运行时注解处理器，构造表创建语句
 */
@Slf4j
public class TableCreator {


    public static String createTableSql(String className) throws ClassNotFoundException {

        Class<?> clazz = Class.forName(className);

        DbTable dbTable = clazz.getAnnotation(DbTable.class);

        if(dbTable == null){
            log.error("dbTable is null.");
            return null;
        }

        String tableName = dbTable.name();
        if(tableName.length() < 1){
            tableName = clazz.getName().toUpperCase();
        }
        List<String> columnDefs = new ArrayList<>();
        //通过Class类API获取到所有成员字段
        for(Field field: clazz.getDeclaredFields()){
            String columnName = null;
            Annotation[] anns = field.getDeclaredAnnotations();
            if(anns.length <1){
                continue;
            }
            if(anns[0] instanceof SQLString){
                SQLString sqlString = (SQLString)anns[0];

                if(sqlString.name().length()<1){
                    columnName = field.getName().toUpperCase();
                }else{
                    columnName = sqlString.name().toUpperCase();
                }
                columnDefs.add(columnName +" VARCHAR("+ sqlString.value()+") "+ getContraints(sqlString.constraint())+", \r\n" );
            }
            if(anns[0] instanceof SQLInterger){
                SQLInterger sqlInterger = (SQLInterger)anns[0];

                if(sqlInterger.name().length()<1){
                    columnName = field.getName().toUpperCase();
                }else{
                    columnName = sqlInterger.name().toUpperCase();
                }
                columnDefs.add(columnName +" INT"+ getContraints(sqlInterger.constraint())+", \r\n" );
            }
        }

        StringBuilder createCommand = new StringBuilder();
        createCommand.append("Create table "+ tableName+" (");
        columnDefs.stream().forEach(s -> createCommand.append(s));
        createCommand.append(" );");
        return createCommand.toString();
    }


    private static String getContraints(Constraints constraint){
        String constraints = "";

        if(constraint.primaryKey()){
            constraints +=" primary key ";
        }
        if(!constraint.allowNull()){
            constraints +=" NOT null ";
        }
        if(constraint.unique()){
            constraints +=" UNIQUE ";
        }
        return constraints;
    }

    public static void main(String[] args) throws Exception{

        System.out.println( createTableSql("com.spring.test.annotation.demo.Member"));

    }


}
