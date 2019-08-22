package com.spring.test.springNewFeature.aop.monitorAop;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy implements InvocationHandler {

    private A target;

    public JDKProxy(A target) {
        this.target = target;
    }

    /**
     *调用被代理类(目标对象)的任意方法都会触发invoke方法
     * @param proxy  代理类
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if("execute".equalsIgnoreCase(method.getName())){
            //...//调用前验证权限
            System.out.println("调用前验证权限");
            Object result = method.invoke(target, args);
            //...//记录日志数据
            System.out.println("记录日志数据");
            return result;
        }
        return method.invoke(target, args);
    }

    /**
     * create proxy class
     * 利用Proxy#newProxyInstance方法便可以动态生成代理对象(proxy)，底层通过反射实现的
     * @return
     */
    public ExInterface createProxy(){
        return (ExInterface) Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }


    public static void main(String[] args) {

        A a = new A();
        JDKProxy jdkProxy = new JDKProxy(a);
        ExInterface proxy=jdkProxy.createProxy();
        proxy.execute();
    }
}
