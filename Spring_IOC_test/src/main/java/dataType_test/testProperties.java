package dataType_test;

import java.util.Iterator;
import java.util.Properties;


public class testProperties {
	Properties properties;//属性类对象properties
	public void print_All(){
		//输出属性类对象properties的内容
		Iterator iterator = properties.keySet().iterator();
		while (iterator.hasNext()){
			Object key = iterator.next();
			Object value = properties.get(key);
			System.out.println("key = " + key + "value = " + value);
		}
	}
	public Properties getProperties() {
		return properties;
	}
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}
