package run.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class runit_propertiesBean {

	String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	String password;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");		
		runit_propertiesBean runit =(runit_propertiesBean)app.getBean("show_db_info");
		System.out.println(runit.username + " " + runit.password);
	}

}
