package com.spring.test.design.Observe;

import org.apache.commons.lang3.StringUtils;

public class ConcreteAllyControlCenter extends AllyControlCenter{

    @Override
    public void notifyObservers(String name) {
        System.out.println(this.allyName+" 战队紧急通知，盟友:"+ name +"遭受攻击");
        for(Observer obs: players){
            if(!StringUtils.equalsAnyIgnoreCase(obs.getName(), name)){
                obs.help();
            }
        }
    }
}
