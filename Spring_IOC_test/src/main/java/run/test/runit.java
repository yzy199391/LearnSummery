package run.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dataType_test.test;

public class runit {
	
	test test_ref;
	
	public test getTest_ref() {
		return test_ref;
	}

	public void setTest_ref(test test_ref) {
		this.test_ref = test_ref;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		runit runit_ref = (runit) context.getBean("runit_ref");
		runit_ref.getTest_ref().printAll();
	}

}
