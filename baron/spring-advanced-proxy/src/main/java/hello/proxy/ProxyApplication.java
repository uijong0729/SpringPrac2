package hello.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import hello.proxy.config.v1_dynamicproxy.DynamicProxyBasicConfig;
import hello.proxy.config.v1_dynamicproxy.DynamicProxyFilterConfig;
import hello.proxy.config.v1_proxy.ConcreteProxyConfig;
import hello.proxy.config.v1_proxy.InterfaceProxyConfig;
import hello.proxy.config.v1_proxyfactory.ProxyFactoryConfigV1;
import hello.proxy.config.v2_proxyfactory.ProxyFactoryConfigV2;
import hello.proxy.config.v4_postprocessor.BeanPostProcessorConfig;
import hello.trace.logtrace.LogTrace;
import hello.trace.logtrace.ThreadLocalLogTrace;

/**
 * @Import 컴포넌트 스캔 대상 외인경우 클래스를 스프링 빈으로 수동 등록한다. 일반적으로 @Configuration같은 설정파일을
 *         등록할 때 사용하지만 스프링 빈을 등록할 때도 사용할 수 있다.
 */
//@Import({ AppV2Config.class, AppV1Config.class })
//@Import(InterfaceProxyConfig.class)
//@Import(ConcreteProxyConfig.class)
//@Import(ProxyFactoryConfigV1.class) // http://localhost:8080/v1/no-log   http://localhost:8080/v1/request?itemId=333
//@Import(ProxyFactoryConfigV2.class)	//   http://localhost:8080/v2/no-log   http://localhost:8080/v2/request?itemId=333
@Import(BeanPostProcessorConfig.class)	//   http://localhost:8080/v2/no-log   http://localhost:8080/v2/request?itemId=333
@SpringBootApplication(scanBasePackages = "hello.proxy.app") // 스캔 범위주의
public class ProxyApplication {

	/**
	 * http://localhost:8080/
	 */
	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}
	
	@Bean
	public LogTrace logTrace() {
		// Thread ID의 동시성 문제를 해결하기위해 TraceID를 ThreadLocal로 감싼 객체
		return new ThreadLocalLogTrace();
	}
}
