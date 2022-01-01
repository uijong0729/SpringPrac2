package hello.proxy.pureproxy.config.v1_dynamicproxy.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.springframework.util.PatternMatchUtils;

import hello.proxy.pureproxy.trace.TraceStatus;
import hello.proxy.pureproxy.trace.logtrace.LogTrace;

/**
 * 
 */
public class LogTraceFilterHandler implements InvocationHandler {

	private final Object target;
	private final LogTrace logTrace;
	private final String[] patterns;
	
	public LogTraceFilterHandler(Object target, LogTrace logTrace, String[] patterns) {
		super();
		this.target = target;
		this.logTrace = logTrace;
		this.patterns = patterns;
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 메서드 이름 필터
		if(isNoProxy(method)) {
			// 프록시 로직을 적용하지않고 그냥 원본 그대로 호출
			return method.invoke(target, args);
		}
		
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
	
	/**
	 * @param method
	 * @return 지정된 패턴에 부합하지 않는 메서드 이름은 true를 반환한다.
	 */
	private boolean isNoProxy(Method method) {
		String methodName = method.getName();
		
		// 스프링이 제공하는 PatternMatchUtils.simpleMatch()를 사용하면
		// 단순한 매칭 로직을 쉽게 적용할 수 있다.
		if (PatternMatchUtils.simpleMatch(this.patterns, methodName) == false) {
			return true;
		}
		return false;
	}

}
