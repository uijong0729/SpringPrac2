package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator implements Component{

	private Component component;
	
	public MessageDecorator(Component component) {
		super();
		this.component = component;
	}

	/**
	 *	원래의 처리에 데코레이팅해서 다시 반환
	 */
	@Override
	public String operation() {
		// TODO Auto-generated method stub
		log.info("Message Decorator 실행");
		String result = "*********";
		return result += component.operation();
	}

}
