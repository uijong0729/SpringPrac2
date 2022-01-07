package hello.proxy.advisor;

import java.lang.reflect.Method;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;

/**
 * <포인트 컷>
	- 어디에 부가기능을 적용할지, 어디에 부가기능을 적용하지 않을지 판단하는 필터링 로직
	- 주로 클래스와 메서드 이름으로 필터링한다.
	- 포인트 컷은 대상 여부를 확인하는 필터 역할만 담당해야한다.
	- 필터링이 작동하면 프록시 로직이 패스될 뿐, 원래의 로직은 그대로 실행된다.
 *
 */
public class AdvisorTest {

	//@Test
	void advisorTest1() {
		ServiceInterface target = new ServiceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		
		// 인터페이스의 가장 일반적인 구현체이다.
		// 생성자를 통해 하나의 포인트 컷과 하나의 어드바이스를 넣어주면 된다.
		// 어드바이저는 하나의 포인트 컷과 하나의 어드바이스로 구성된다.
		// Pointcut.TRUE => 필터링 없음
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice());
		
		// 프록시 팩토리를 사용할 때에는 Advisor가 필수이다.
		// Advice를 매개변수로 넘겼을 경우, 포인트 컷이 항상 TRUE인 Advisor를 생성하여 동작한다.
		proxyFactory.addAdvisor(advisor);
		ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
		
		proxy.save();
		proxy.find();
	}	
	
	@Test
	@DisplayName("직접 만든 포인트 컷")
	void advisorTest2() {
		ServiceInterface target = new ServiceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		
		// 인터페이스의 가장 일반적인 구현체이다.
		// 생성자를 통해 하나의 포인트 컷과 하나의 어드바이스를 넣어주면 된다.
		// 어드바이저는 하나의 포인트 컷과 하나의 어드바이스로 구성된다.
		// 메소드 이름이 save일 때만 TimeProxy를 호출하는 포인트컷을 적용
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new MyPointcut(), new TimeAdvice());
		
		// 프록시 팩토리를 사용할 때에는 Advisor가 필수이다.
		// Advice를 매개변수로 넘겼을 경우, 포인트 컷이 항상 TRUE인 Advisor를 생성하여 동작한다.
		proxyFactory.addAdvisor(advisor);
		ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
		
		proxy.save();
		proxy.find();
	}	
	
	@Slf4j
	static class MyPointcut implements Pointcut {

		@Override
		public ClassFilter getClassFilter() {
			// TODO Auto-generated method stub
			return ClassFilter.TRUE;
		}

		@Override
		public MethodMatcher getMethodMatcher() {
			// TODO Auto-generated method stub
			return new MethodMatcher() {
				
				private String matchedName = "save";
				
				@Override
				public boolean matches(Method method, Class<?> targetClass, Object... args) {
					return false;
				}
				
				@Override
				public boolean matches(Method method, Class<?> targetClass) {
					log.info("포인트컷 호출 method={} target={}", method.getName(), targetClass);
					boolean result = method.getName().equals(matchedName);
					log.info("포인트컷 결과 result={}", result);
					return result;
				}
				
				@Override
				public boolean isRuntime() {
					// TODO Auto-generated method stub
					return false;
				}
			};
		}
	}
}
