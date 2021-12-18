package hello.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheProxy implements Subject {

	/**
	 * 프록시가 접근하는 실제 객체
	 */
	private Subject target;
	
	/**
	 * 프록시가 갖는 캐시
	 */
	private String cacheValue;
	
	
	/**
	 * 클라이언트가 프록시를 참조하게 만들고
	 * 프록시는 ReadlSubject를 참조하게 만든다
	 * 
	 * @param target
	 */
	public CacheProxy(Subject target) {
		super();
		this.target = target;
	}


	@Override
	public String operation() {
		log.info("프록시 호출");
		
		// 클라이언트가 프록시를 호출하면 프록시가 최종적으로 실제 객체를 호출해야하기 때문에
		// 내부에 실제 객체의 참조를 가지고 있는다
		if (cacheValue == null) {
			cacheValue = target.operation();
		}
		
		return cacheValue;
	}

}
