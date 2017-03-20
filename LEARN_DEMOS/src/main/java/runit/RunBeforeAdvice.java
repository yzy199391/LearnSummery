package runit;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.interfacepackage.my_interface;

import java.lang.reflect.Method;

/**
 * Created by hasee on 2017/3/8.
 */
public class RunBeforeAdvice implements MethodBeforeAdvice{
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("准备打印--在advice执行之前！");
    }

    public static void main(String[] args){
        ApplicationContext app = new ClassPathXmlApplicationContext("spring-config.xml");
       my_interface runit = (my_interface)app.getBean("my_proxy");
        runit.printHello();
    }
}
