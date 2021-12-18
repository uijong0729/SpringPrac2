package hello.proxy.pureproxy.proxy;

import org.junit.jupiter.api.Test;

import hello.proxy.pureproxy.proxy.code.CacheProxy;
import hello.proxy.pureproxy.proxy.code.ProxyPatternClient;
import hello.proxy.pureproxy.proxy.code.RealSubject;

public class ProxyPatternTest {

	@Test
	void noProxyTest() {
		RealSubject realSubject = new RealSubject();
		ProxyPatternClient client = new ProxyPatternClient(realSubject);
		
		// 3번 호출에 총 3초의 시간이 걸린다.
		client.execute();
		client.execute();
		client.execute();
	}
	

	/**
	 *	프록시 패턴의 핵심은 기존의 코드를 전혀 변경하지 않고 접근제어(캐싱)를 했다는 점이다. 
	 */
	@Test
	void cacheProxyTest() {
		RealSubject realSubject = new RealSubject();
		CacheProxy proxy = new CacheProxy(realSubject);
		ProxyPatternClient client = new ProxyPatternClient(proxy);
		
		// 3번 호출에 총 1초의 시간이 걸린다.
		client.execute();
		client.execute();
		client.execute();
	}
}
