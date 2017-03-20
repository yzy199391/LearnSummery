package runit;


import test.implementclass.my_interface_imple;
import test.interfacepackage.my_interface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理实现
 */
public class RunDynamicProxy implements InvocationHandler{

    Object any_object;

    //接口绑定
    public Object bind(Object any_object){
        this.any_object = any_object;
        return Proxy.newProxyInstance(any_object.getClass().getClassLoader(), any_object.getClass().getInterfaces(), this);
    }

    //方法增强
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object return_object=null;
        System.out.println("准备打印");
        return_object = method.invoke(any_object,args);
        System.out.println("结束打印");
        return return_object;
    }

    public static void main(String[] args){
        RunDynamicProxy runit = new RunDynamicProxy();
        my_interface my_interface_ref = (my_interface)runit.bind(new my_interface_imple());
        my_interface_ref.printHello();

    }
}
