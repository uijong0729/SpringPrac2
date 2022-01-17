package hello.proxy.config.v4_postprocessor.postprocessor;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import lombok.extern.slf4j.Slf4j;

/**
 *	BeanPostProcessor를 구현하여 bean으로 등록하는 것으로
 *	프록시를 등록하는 코드가 설정파일에는 필요없어진다.
 */
@Slf4j
public class PackageLogTracePostProcessor implements BeanPostProcessor {

	private final String basePackage;
	private final Advisor advisor;
	
	public PackageLogTracePostProcessor(String basePackage, Advisor advisor) {
		super();
		this.basePackage = basePackage;
		this.advisor = advisor;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		log.info("param beanName={} bean={}", beanName, bean.getClass());
		
		//프록시 적용 대상 여부 체크 (포인트컷을 활용하지 못한 예)
		//  - 스프링 빈은 무수히 많기 때문에 모든 스프링 빈에 프록시를 적용할 필요가 없다.
		//  - 스프링 AOP는 포인트 컷을 사용해서 프록시 적용 대상 여부를 체크한다.
		//  - 프록시의 어떤 메서드가 호출되었을때 어드바이스를 적용할지 판단할 수 있다.
		String packageName = bean.getClass().getPackageName();
		if (!packageName.startsWith(this.basePackage)) {
			// 프록시 적용 대상이 아니면 원본을 그대로 진행
			return bean;
		}
		
		//프록시 대상이면 프록시를 만들어서 반환
		ProxyFactory factory = new ProxyFactory(bean);
		factory.addAdvisor(this.advisor);
		
		Object proxy = factory.getProxy();
		log.info("create proxy: target={} proxy={}", bean.getClass(), proxy.getClass());
		return proxy;
	}
	
	
}
