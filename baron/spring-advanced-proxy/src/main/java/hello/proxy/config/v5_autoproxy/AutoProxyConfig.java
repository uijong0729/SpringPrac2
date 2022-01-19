package hello.proxy.config.v5_autoproxy;

import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import hello.proxy.config.v1_proxyfactory.advice.LogTraceAdvice;
import hello.proxy.config.v4_postprocessor.BeanPostProcessorConfig;
import hello.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AutoProxyConfig {
	
	/**
	 * 1. 프록시를 생성하는 단계
	 * - advisor(포인트컷 + 어드바이스)만 빈으로 등록하면 자동으로 프록시를 생성해준다. 
	 *    (v4_postprocessor패키지와 같이 PostProcessor를 등록할 필요 없음)
	 * - 포인트 컷 조건에 하나라도 맞는 것이 있다면 프록시를 생성한다.
	 * 
	 * 2. 프록시 실제 호출시 어드바이스 적용 단계
	 * - 프록시는 이미 만들어진 상태지만, 부가기능인 어드바이스를 적용할지 말지를 포인트 컷을 보고 판단한다.
	 * 
	 * @param logtrace
	 * @return
	 */
	@Bean
	public Advisor advisor1(LogTrace logtrace) {
		// Point-Cut
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.setMappedNames("request*", "order*", "save*"); // noLog()는 필터링
		
		// advice
		//  : 인터페이스 MethodInterceptor를 구현한 클래스 
		LogTraceAdvice advice = new LogTraceAdvice(logtrace);
		
		// advisor
		return new DefaultPointcutAdvisor(pointcut, advice);
	}
}
