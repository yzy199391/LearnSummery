package runit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.interfacepackage.my_interface;

/**
 * Created by hasee on 2017/3/1.
 */
public class RunMultiConf {

    public my_interface getMi() {
        return mi;
    }

    public void setMi(my_interface mi) {
        this.mi = mi;
    }

    my_interface mi;

    public static void main(String[] args){
        ApplicationContext app = new ClassPathXmlApplicationContext("spring-config.xml");
        RunMultiConf run = (RunMultiConf)app.getBean("runit");
        run.getMi().printHello();

        RunMultiConf run2 = (RunMultiConf)app.getBean("runit2");
        run2.getMi().printHello();
    }
}
