package run.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dataOperator.IDataOperator;

public class Run {
	private IDataOperator dataOperator;

	public IDataOperator getDataOperator() {
		return dataOperator;
	}

	public void setDataOperator(IDataOperator dataOperator) {
		this.dataOperator = dataOperator;
	}
	
	public static void main(String[] args) {
		ApplicationContext apc = new ClassPathXmlApplicationContext("applicationContext.xml");
		Run run_ref = (Run)apc.getBean("run");
		run_ref.getDataOperator().save("hello yzy!");
	}
}
