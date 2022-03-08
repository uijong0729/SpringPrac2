package com.example.demo.pointcut;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.example.demo.order.aop.member.MemberService;
import com.example.demo.order.aop.member.annotation.ClassAop;
import com.example.demo.order.aop.member.annotation.MethodAop;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@Import(ParameterTest.ParameterAspect.class)
public class ParameterTest {
	@Autowired
	MemberService memberService;
	
	/**
	 * 프록시가 잘 적용되어있나 확인
	 */
	@Test
	public void success() {
		log.info("memberService proxy={}", memberService.getClass());
		memberService.hello("hello A");
	}
	
	/**
	 * Aspect는 빈으로 등록해야 적용된다
	 */
	@Slf4j
	@Aspect
	static class ParameterAspect {
		@Pointcut("execution(* com.example.demo.order.aop.member..*.*(..))")
		private void allmember() {}
		
		/**
		 * @param joinPoint
		 * @return
		 * @throws Throwable
		 */
		@Around("allmember()")
		public Object logArgs1(ProceedingJoinPoint joinPoint) throws Throwable {
			// hello A
			Object arg1 = joinPoint.getArgs()[0];
			log.info("[logArgs1]{} arg={}", joinPoint.getSignature(), arg1);
			return joinPoint.proceed();
		}
		
		/**
		 * @param joinPoint
		 * @param arg			Around 내 구문 args를 사용한 뒤, 인수를 지정하면 해당 인수에 값이 담겨져 들어온다. 
		 * @return
		 * @throws Throwable
		 */
		@Around("allmember() && args(arg,..)")
		public Object logArgs2(ProceedingJoinPoint joinPoint, String arg) throws Throwable {
			// hello A
			log.info("[logArgs2]{} arg={}", joinPoint.getSignature(), arg);
			return joinPoint.proceed();
		}
		
		/**
		 * @param joinPoint
		 * @param arg
		 * @return
		 * @throws Throwable
		 */
		@Before("allmember() && args(arg,..)")
		public void logArgs3(String arg) {
			// hello A
			log.info("[logArgs3] arg={}", arg);
		}
		
		/**
		 * @param joinPoint
		 * @param obj
		 * 
		 * this나 target은 대상을 직접 지정한다. (*와 같은 패턴은 사용할 수 없다)
		 * 실제 대상 구현체 class com.example.demo.order.aop.member.MemberServiceImpl
		 * - 참고 : 일반적으로 스프링AOP를 적용하면 스프링 빈으로 등록되는 객체는 실제 대상 구현체가 아님
		 * - 대상 하나를 정확하게 지정하기 때문에 많이 사용하진 않는다.
		 * - 프록시 생성 전략(JDK / CGLIB)에 따라 포인트컷의 대상이 안될 수 도 있다.
		 */
		@Before("allmember() && this(obj)")
		public void thisArgs(JoinPoint joinPoint, MemberService obj) {
			// hello A
			log.info("[this]{} obj={}", joinPoint.getSignature(), obj.getClass());
		}
		
		/**
		 * @param joinPoint
		 * @param obj
		 * 
		 * this나 target은 대상을 직접 지정한다. (*와 같은 패턴은 사용할 수 없다)
		 * CGLIB프록시 객체 class com.example.demo.order.aop.member.MemberServiceImpl$$EnhancerBySpringCGLIB$$cc8ced70
		 * - 참고 : 일반적으로 스프링AOP를 적용하면 스프링 빈으로 등록되는 객체는 실제 대상 구현체가 아님
		 * - 대상 하나를 정확하게 지정하기 때문에 많이 사용하진 않는다.
		 * - 프록시 생성 전략(JDK / CGLIB)에 따라 포인트컷의 대상이 안될 수 도 있다.
		 */
		@Before("allmember() && target(obj)")
		public void targetArgs(JoinPoint joinPoint, MemberService obj) {
			// hello A
			log.info("[target]{} obj={}", joinPoint.getSignature(), obj.getClass());
		}
		
		/**
		 * @param joinPoint
		 * @param annotation
		 * 
		 * 클래스 애노테이션 정보를 직접매칭
		 */
		@Before("allmember() && @target(annotation)") // @target()안의 이름은 인수명과 동일하면 됨
		public void atTargetArgs(JoinPoint joinPoint, ClassAop annotation) {
			// hello A
			log.info("[@target]{} obj={}", joinPoint.getSignature(), annotation.getClass());
		}
		
		@Before("allmember() && @within(annotation)") // @within()안의 이름은 인수명과 동일하면 됨
		public void atWithinArgs(JoinPoint joinPoint, ClassAop annotation) {
			// hello A
			log.info("[@within]{} obj={}", joinPoint.getSignature(), annotation.getClass());
		}
		
		@Before("allmember() && @annotation(annotation)") // @annotation()안의 이름은 인수명과 동일하면 됨
		public void atAnnotationArgs(JoinPoint joinPoint, MethodAop annotation) {
			// MethodAop 애노테이션 안에 담고있는 값을 가져올 수 있다.
			
//			@MethodAop("Test Value")
//			public String hello(String msg) {
//				// TODO Auto-generated method stub
//				return "ok";
//			}
			
			// annotation.value() == "Test Value"
			log.info("[@annotation]{} getValue={}", joinPoint.getSignature(), annotation.value());
		}
	}
}
