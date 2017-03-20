package runit;

import test.implementclass.StaticProxyImpl;
import test.interfacepackage.StaticProxyInterface;

/**
 * Created by hasee on 2017/3/6.
 */
public class RunStaticProxy implements StaticProxyInterface{
    StaticProxyInterface staticProxyInterface;//声明接口，代码由此获取代理类的实现

    public RunStaticProxy(StaticProxyInterface staticProxyInterface){//构造方法
        super();
        this.staticProxyInterface = staticProxyInterface;
    }
    @Override
    public void say_hello() {//实现方法
        System.out.println("准备Hello");
        staticProxyInterface.say_hello();
        System.out.println("结束Hello");
    }

    public static void main(String[] args){
        StaticProxyInterface runit = new RunStaticProxy(new StaticProxyImpl());
        runit.say_hello();
    }
}
