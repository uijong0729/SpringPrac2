package hello.proxy.pureproxy.decorator;

import org.junit.jupiter.api.Test;

import hello.proxy.pureproxy.decorator.code.Component;
import hello.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import hello.proxy.pureproxy.decorator.code.MessageDecorator;
import hello.proxy.pureproxy.decorator.code.RealComponent;
import hello.proxy.pureproxy.decorator.code.TimeDecorator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DecoratorPatternTest {

	/**
	 * client -> component 의존관계
	 */
	@Test
	void noDecorator() {
		RealComponent component = new RealComponent();
		DecoratorPatternClient client = new DecoratorPatternClient(component);
		client.execute();
	}
	
	@Test
	void decorator1() {
		Component component = new RealComponent(); 
		Component messageDecorator = new MessageDecorator(component);
		DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator); 
		client.execute();
	}
	
	/**
	 * client -> TimeDecorator -> MessageDecorator -> RealComponent
	 * 
	 * Client 클래스의 코드수정없이 부가기능을 게속 추가하는 패턴
	 */
	@Test
	void decorator2() {
		Component component = new RealComponent(); 
		Component messageDecorator = new MessageDecorator(component);
		Component timeDecorator = new TimeDecorator(messageDecorator);
		DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator); 
		client.execute();
	}
}
