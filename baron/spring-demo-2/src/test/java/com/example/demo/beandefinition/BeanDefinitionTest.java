package com.example.demo.beandefinition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.demo.AppConfig;

public class BeanDefinitionTest {
	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
	
	@Test
	void findApplicationBean() {
		String[] defs = ac.getBeanDefinitionNames();
		for (String def : defs) {
			BeanDefinition bd = ac.getBeanDefinition(def);
			
			if (bd.getRole() == BeanDefinition.ROLE_APPLICATION) {
				// memberRepositoryRoot 메타정보
				//		bean: class [null]; 
				//		scope=; 
				//		abstract=false; 
				//		lazyInit=null; 
				//		autowireMode=3; 
				//		dependencyCheck=0; 
				//		autowireCandidate=true; 
				//		primary=false; 
				//		factoryBeanName=appConfig; 
				//		factoryMethodName=memberRepository; 
				//		initMethodName=null; 
				//		destroyMethodName=(inferred); 
				//		defined in com.example.demo.AppConfig
				System.out.println(def + bd);
			}
		}
	}
}	
