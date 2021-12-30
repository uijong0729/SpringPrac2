package hello.proxy.jdkdynamic;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReflectionTest {

	@Test
	void reflection1() throws Exception {
		// 클래스정보. 내부클래스는 $로 접근
		Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
		Hello target = new Hello();
		
//		// ===== CallA메서드 정보
		Method methodCallA = classHello.getMethod("callA");
//		// target 인스턴스에 있는 메서드를 호출한다.
//		Object result1 = methodCallA.invoke(target);
//		log.info("result1 = {}", result1);
//		
//		// ===== CallB메서드 정보
		Method methodCallB = classHello.getMethod("callB");
//		// target 인스턴스에 있는 메서드를 호출한다.
//		Object result2 = methodCallB.invoke(target);
//		log.info("result2 = {}", result2);
		
		dynamicCall(methodCallA, target);
		dynamicCall(methodCallB, target);
	}
	
	/**
	 * 기존에는 메서드 이름을 직접 호출했지만 Method 메타정보를 통해서 호출할 메서드 정보가 동적으로 제공된다.
	 * 리플렉션은 런타임에 동작하기 때문에 컴파일 시점에 오류를 잡을 수 없다. -> 일반적으로 사용하면 안된다.
	 * 
	 * @param method (리플렉션)
	 * @param target
	 * @throws Exception
	 */
	private void dynamicCall(Method method, Object target) throws Exception{
		log.info("start");
		Object invoke = method.invoke(target);
		log.info("end={}", invoke);
	}
	
	@Slf4j
	static class Hello {
		public String callA() {
			log.info("call A");
			return "A";
		}
		
		public String callB() {
			log.info("call B");
			return "B";
		}
	}
}
