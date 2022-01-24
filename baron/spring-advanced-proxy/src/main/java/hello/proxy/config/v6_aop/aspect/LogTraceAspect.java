package hello.proxy.config.v6_aop.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import hello.trace.TraceStatus;
import hello.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class LogTraceAspect {
	
	private final LogTrace logTrace;

	public LogTraceAspect(LogTrace logTrace) {
		super();
		this.logTrace = logTrace;
	}
	
	/**
	 * @Around 포인트 컷
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* hello.proxy.app..*(..))")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
		TraceStatus status = null;
		try {
			String message = joinPoint.getSignature().toShortString();
			status = logTrace.begin(message);
			
			// 로직 호출
			Object result = joinPoint.proceed();
			
			logTrace.end(status);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			logTrace.exception(status, e);
			throw e;
		}
	}
}
