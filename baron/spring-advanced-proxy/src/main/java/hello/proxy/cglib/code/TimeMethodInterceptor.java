package hello.proxy.cglib.code;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeMethodInterceptor implements MethodInterceptor {

	private final Object target;
	
	public TimeMethodInterceptor(Object target) {
		super();
		this.target = target;
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		log.info("TimeProxy 실행");
		long start = System.currentTimeMillis();
		
		Object result = methodProxy.invoke(target, args);	// CGLIB에서는 Method를 사용해도 되지만,
//		Object result = method.invoke(target, args);		// MethodProxy 사용을 권장한다고 한다.
		
		long end = System.currentTimeMillis();
		log.info("TimeProxy 종료 : resultTime={}", (end - start));
		return result;
	}

}
