package spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplicationContextExample {

	public static void main(String[] args) {
		new SpringApplicationContextExample();
	}

	public SpringApplicationContextExample() {
		// open/read the application context file
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

}