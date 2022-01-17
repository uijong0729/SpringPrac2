package hello.proxy.config.v4_postprocessor;

import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import hello.proxy.config.v1_proxyfactory.advice.LogTraceAdvice;
import hello.proxy.config.v4_postprocessor.postprocessor.PackageLogTracePostProcessor;
import hello.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class BeanPostProcessorConfig {

	@Bean
	public PackageLogTracePostProcessor logTracePostProcessor(LogTrace logtrace) {
		return new PackageLogTracePostProcessor("hello.proxy.app", getAdvisor(logtrace));
		
	}

	private Advisor getAdvisor(LogTrace logtrace) {
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
