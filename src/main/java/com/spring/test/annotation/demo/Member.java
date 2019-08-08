package com.spring.test.annotation.demo;


import lombok.Data;

@Data
@DbTable(name = "MEMBER")
public class Member {

    @SQLString(name="id", value =16, constraint = @Constraints(primaryKey = true, unique = true))
    private String id;

    @SQLString(name="name", value =16)
    private String name;
    
    @SQLInterger(name="age")
    private int age;

    @SQLString(name="desc", value =64, constraint = @Constraints(allowNull = true))
    private String description;

}
