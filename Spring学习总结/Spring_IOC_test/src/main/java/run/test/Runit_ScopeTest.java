package run.test;


import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Runit_ScopeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		try{
		Date date = (Date)app.getBean("my_date");
		date = (Date)app.getBean("my_date");
		Thread.sleep(3000);
		System.out.println(date.toString());
		date = (Date)app.getBean("my_date");
		Thread.sleep(3000);
		System.out.println(date.toString());
		date = (Date)app.getBean("my_date");
		System.out.println(date.toString());

		}catch (Exception e) {
			// TODO: handle exception
		}		
	}

}
