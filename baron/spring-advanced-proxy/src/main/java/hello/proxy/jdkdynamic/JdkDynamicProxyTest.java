package hello.proxy.jdkdynamic;

import java.lang.reflect.Proxy;

import org.junit.jupiter.api.Test;

import hello.proxy.jdkdynamic.code.AImpl;
import hello.proxy.jdkdynamic.code.AInterface;
import hello.proxy.jdkdynamic.code.BImpl;
import hello.proxy.jdkdynamic.code.BInterface;
import hello.proxy.jdkdynamic.code.TimeInvocationHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * TimeInvocationHandler 외에는 따로 클래스를 만들지 않았지만 각각 중복된 동작을 수행하는 프록시를 동적으로 만들어낸다.
 */
@Slf4j
public class JdkDynamicProxyTest {
	
	@Test
	void dynamicA() {
		AInterface target = new AImpl();
		TimeInvocationHandler handler = new TimeInvocationHandler(target);
		
		
		// arg1:AInterface.class					=>	프록시가 어디에 생성될지 ClassLoader를 지정
		// arg2:new Class[] {AInterface.class}		=>	어떤 인터페이스 기반으로 프록시 클래스를 만들지 지정(여러개 지정가능)
		// arg3:handler 							=>	프록시가 호출할 로직
		AInterface proxy = (AInterface) Proxy.newProxyInstance(
				AInterface.class.getClassLoader(), 
				new Class[] {AInterface.class}, 
				handler);
		
		
		// proxy.call() -> Handler의 로직(invoke)을 실행한다
		proxy.call();
		log.info("\ntargetClass={}\nproxyClass={}", target.getClass(), proxy.getClass());
	}
	
	@Test
	void dynamicB() {
		BInterface target = new BImpl();
		TimeInvocationHandler handler = new TimeInvocationHandler(target);
		
		
		// arg1:AInterface.class					=>	프록시가 어디에 생성될지 ClassLoader를 지정
		// arg2:new Class[] {BInterface.class}		=>	어떤 인터페이스 기반으로 프록시 클래스를 만들지 지정(여러개 지정가능)
		// arg3:handler 							=>	프록시가 호출할 로직
		BInterface proxy = (BInterface) Proxy.newProxyInstance(
				AInterface.class.getClassLoader(), 
				new Class[] {BInterface.class}, 
				handler);
		
		
		// proxy.call() -> Handler의 로직(invoke)을 실행한다
		proxy.call();
		log.info("\ntargetClass={}\nproxyClass={}", target.getClass(), proxy.getClass());
	}
}
