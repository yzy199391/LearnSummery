package run.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dataType_test.testNull;

public class runit_null {

	testNull testNull_ref;
	
	public testNull getTestNull_ref() {
		return testNull_ref;
	}

	public void setTestNull_ref(testNull testNull_ref) {
		this.testNull_ref = testNull_ref;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		runit_null runit_ref = (runit_null) context.getBean("runit_null_ref");
		runit_ref.getTestNull_ref().print_null_string();
	}

}
