package com.spring.test.design.Observe;

public class Player implements Observer {

    public Player(String name){
        this.name = name;
    }
    private String name;

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    @Override
    public void help() {
        System.out.println("坚持，"+ this.getName()+" 来救你");
    }

    @Override
    public void beAttacked(AllyControlCenter acc) {
        System.out.println(this.name + "被攻击！");
        acc.notifyObservers(name);
    }
}
