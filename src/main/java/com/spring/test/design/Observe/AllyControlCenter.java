package com.spring.test.design.Observe;

import java.util.ArrayList;
import java.util.List;

public abstract class AllyControlCenter {

    protected String allyName;

    protected List<Observer> players = new ArrayList<>();

    public String getAllyName() {
        return allyName;
    }

    public void setAllyName(String allyName) {
        this.allyName = allyName;
    }

    //register
    public void join(Observer obs){
        System.out.println(obs.getName()+"加入"+this.allyName+"战队");
        players.add(obs);
    }

    //destroy
    public void quit(Observer obs){
        System.out.println(obs.getName()+"退出"+this.allyName+"战队");
        players.remove(obs);
    }

    //notify
    public abstract void notifyObservers(String name);

}
