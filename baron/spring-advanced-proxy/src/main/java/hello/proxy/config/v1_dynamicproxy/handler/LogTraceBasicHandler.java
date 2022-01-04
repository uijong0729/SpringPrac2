package hello.proxy.config.v1_dynamicproxy.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import hello.trace.TraceStatus;
import hello.trace.logtrace.LogTrace;

/**
 * 모든 메서드에 적용되는 문제점이 있음 -> noLog() 메소드도 호출
 */
public class LogTraceBasicHandler implements InvocationHandler {

	private final Object target;
	private final LogTrace logTrace;
	
	public LogTraceBasicHandler(Object target, LogTrace logTrace) {
		super();
		this.target = target;
		this.logTrace = logTrace;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		TraceStatus status = null;
		try {
			
			String msg = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "() with Proxy";
			status = logTrace.begin(msg);
			// 로직 호출
			Object result = method.invoke(target, args);
			logTrace.end(status);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			logTrace.exception(status, e);
			throw e;
		}
	}

}
