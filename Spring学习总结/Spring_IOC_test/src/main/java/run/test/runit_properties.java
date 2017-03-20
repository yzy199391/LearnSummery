package run.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dataType_test.testProperties;

public class runit_properties {

	testProperties test_ref;
	
	public testProperties getTest_ref() {
		return test_ref;
	}

	public void setTest_ref(testProperties test_ref) {
		this.test_ref = test_ref;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		runit_properties runit_ref = (runit_properties)context.getBean("runit_properties_ref");
		runit_ref.getTest_ref().print_All();
		//runit_ref.test_ref.print_All();
	}

}
