package runit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.PropertyTest;

/**
 * Created by hasee on 2017/2/28.
 */
public class RunPropertyTest {
    PropertyTest test;

    public PropertyTest getTest() {
        return test;
    }

    public void setTest(PropertyTest test) {
        this.test = test;
    }

    public static void main(String[] args){
        ApplicationContext app = new ClassPathXmlApplicationContext("spring-config.xml");
        RunPropertyTest run = (RunPropertyTest)app.getBean("PropertyTest");

        System.out.println(run.getTest().getDbconnect());
        System.out.println(run.getTest().getDbname());
        System.out.println(run.getTest().getDbpassword());
    }
}
