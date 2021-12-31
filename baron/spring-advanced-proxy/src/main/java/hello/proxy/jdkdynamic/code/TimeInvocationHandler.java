package hello.proxy.jdkdynamic.code;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;

/**
 * JDK동적프록시 클래스
 */
@Slf4j
public class TimeInvocationHandler implements InvocationHandler{

	// 프록시에는 항상 프록시가 실제로 호출할 대상이 있어야한다.
	private final Object target;
	
	public TimeInvocationHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		log.info("TimeProxy 실행");
		long start = System.currentTimeMillis();
		Object result = method.invoke(target, args);
		long end = System.currentTimeMillis();
		
		log.info("TimeProxy 종료 : resultTime={}", (end - start));
		return result;
	}

}
