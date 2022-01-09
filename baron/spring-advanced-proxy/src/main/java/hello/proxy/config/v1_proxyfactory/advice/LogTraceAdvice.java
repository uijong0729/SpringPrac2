package hello.proxy.config.v1_proxyfactory.advice;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import hello.trace.TraceStatus;
import hello.trace.logtrace.LogTrace;

public class LogTraceAdvice implements MethodInterceptor {
	
	private final LogTrace logTrace;
	
	/**
	 * 프록시팩토리를 사용하면 target을 받지 않아도 된다.
	 * 
	 * @param logTrace
	 */
	public LogTraceAdvice(LogTrace logTrace) {
		super();
		this.logTrace = logTrace;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		TraceStatus status = null;
		try {
			Method method = invocation.getMethod();
			String msg = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "() with Proxy";
			status = logTrace.begin(msg);
			
			// 로직 호출
			Object result = invocation.proceed();
			
			logTrace.end(status);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			logTrace.exception(status, e);
			throw e;
		}
	}

}
