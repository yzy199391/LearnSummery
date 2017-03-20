package test.implementclass;

import test.interfacepackage.StaticProxyInterface;

/**
 * Created by hasee on 2017/3/6.
 */
public class StaticProxyImpl implements StaticProxyInterface {//静态代理实现类
    @Override
    public void say_hello() {
        System.out.println("Hello yzy");
    }
}
