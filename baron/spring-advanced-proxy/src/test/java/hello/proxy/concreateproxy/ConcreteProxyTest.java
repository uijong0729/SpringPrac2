package hello.proxy.concreateproxy;

import org.junit.jupiter.api.Test;

import hello.proxy.concreateproxy.code.ConcreteClient;
import hello.proxy.concreateproxy.code.ConcreteLogic;
import hello.proxy.concreateproxy.code.TimeProxy;

public class ConcreteProxyTest {

	/**
	 * ConcreteClient의 변경없이 프록시를 적용할 수 있을까?
	 */
//	@Test	
	void noProxy() {
		ConcreteLogic logic = new ConcreteLogic();
		ConcreteClient client = new ConcreteClient(logic);
		client.execute();
	}
	
	@Test
	void addProxy() {
		ConcreteLogic logic = new ConcreteLogic();
		TimeProxy proxy = new TimeProxy(logic); 
		
		// ConcreateLogic이 아니라 TimeProxy를 주입하는 부분에 주목
		ConcreteClient client = new ConcreteClient(proxy);
		client.execute();
	}
}
