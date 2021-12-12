package hello.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;

/**
 * @Import 컴포넌트 스캔 대상 외인경우 클래스를 스프링 빈으로 수동 등록한다. 일반적으로 @Configuration같은 설정파일을
 *         등록할 때 사용하지만 스프링 빈을 등록할 때도 사용할 수 있다.
 */
@Import({ AppV2Config.class, AppV1Config.class })
@SpringBootApplication(scanBasePackages = "hello.proxy.app") // 스캔 범위주의
public class ProxyApplication {

	/**
	 * http://localhost:8080/
	 */
	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

}
