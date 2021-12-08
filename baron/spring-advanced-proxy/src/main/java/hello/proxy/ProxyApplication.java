package hello.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "hello.proxy.app") //주의
public class ProxyApplication {

	
	/**
	 * http://localhost:8080/
	 */
	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

}
