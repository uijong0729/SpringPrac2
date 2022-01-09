package hello.proxy.cglib;

import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;

/**
 * CGLIB제약
 * 	- 부모 클래스의 생성자를 체크해야한다. (기본 생성자가 필요하다)
 *  - 클래스, 메서드에 final 키워드가 붙으면 상속을 통한 프록시 구현이 불가능하다.
 */
@Slf4j
public class CglibTest {

	@Test
	void cglib() {
		ConcreteService target = new ConcreteService();
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(ConcreteService.class);	 			// 구체클레스를 기반으로 상속받아 클래스를 동적생성
		enhancer.setCallback(new TimeMethodInterceptor(target));	// 실제 객체 target이 필요함 (MethodInterceptor는 Callback을 상속받은 인터페이스)
		ConcreteService proxy = (ConcreteService) enhancer.create();
		
		// targetClass=class hello.proxy.common.service.ConcreteService
		// proxyClass=class hello.proxy.common.service.ConcreteService$$EnhancerByCGLIB$$25d6b0e3
		log.info("\ntargetClass={}\nproxyClass={}", target.getClass(), proxy.getClass());
		
		proxy.call();
	}
}
