package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author kroch
 * 
 * @Aspect�� ����Ϸ��� @EnableAspectJAutoProxy�� ��������� �߰��ؾ� ������
 * ������ ��Ʈ�� ����ϸ� @EnableAspectJAutoProxy�� �ڵ����� �߰��ȴ�.
 *
 */
@SpringBootApplication
public class SpringAopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAopApplication.class, args);
	}

}
