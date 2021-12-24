package hello.proxy.concreateproxy;

import org.junit.jupiter.api.Test;

import hello.proxy.concreateproxy.code.ConcreteClient;
import hello.proxy.concreateproxy.code.ConcreteLogic;

public class ConcreteProxyTest {

	/**
	 * ConcreteClient의 변경없이 프록시를 적용할 수 있을까?
	 */
	@Test
	void noProxy() {
		ConcreteLogic logic = new ConcreteLogic();
		ConcreteClient client = new ConcreteClient(logic);
		client.execute();
	}
}
