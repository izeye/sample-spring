package learningtest.cli;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class CommandLineArgumentsTest {

	static class Test1 {
		public static String[] ARGS;

		public static void main(String[] args) {
			ARGS = args;

			AbstractApplicationContext context = new ClassPathXmlApplicationContext(
					"context.xml", CommandLineArgumentsTest.class);
			String[] fetchedArgs = context.getBean(String[].class);
			System.out.println(Arrays.asList(fetchedArgs));

			context.close();
		}
	}

	static class Test2 {
		public static void main(String[] args) {
			DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
			BeanDefinition beanDefinition = BeanDefinitionBuilder
					.rootBeanDefinition(Arrays.class, "asList")
					.addConstructorArgValue(args).getBeanDefinition();
			beanFactory.registerBeanDefinition("args", beanDefinition);
			GenericApplicationContext parentContext = new GenericApplicationContext(
					beanFactory);
			parentContext.refresh();

			AbstractApplicationContext context = new ClassPathXmlApplicationContext(
					new String[] { "context2.xml" },
					CommandLineArgumentsTest.class, parentContext);
			@SuppressWarnings("unchecked")
			List<String> fetchedArgs = context.getBean(List.class);
			System.out.println(fetchedArgs);

			context.close();
		}
	}

}
