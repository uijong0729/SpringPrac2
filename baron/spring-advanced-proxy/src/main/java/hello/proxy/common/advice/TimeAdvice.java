package hello.proxy.common.advice;

// 패키지에 주의
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeAdvice implements MethodInterceptor {
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		log.info("TimeProxy 실행");
		long start = System.currentTimeMillis();
		// 프록시임에도 target이 필요없다.
		// 프록시 팩토리의 프록시를 생성하는 과정에서 타겟정보를 넘겨받기 떄문
		Object result = invocation.proceed();	
		long end = System.currentTimeMillis();
		log.info("TimeProxy 종료 : resultTime={}", (end - start));
		return result;
	}

}
