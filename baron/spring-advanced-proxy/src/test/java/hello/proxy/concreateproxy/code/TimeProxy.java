package hello.proxy.concreateproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeProxy extends ConcreteLogic {
	
//	// 프록시이기 때문에 실제 호출할 대상은 갖고 있어야한다.
	private ConcreteLogic logic;

	public TimeProxy(ConcreteLogic logic) {
		super();
		this.logic = logic;
	}

	@Override
	public String operation() {
		log.info("TimeDecorator 실행");
		long startTime = System.currentTimeMillis();
		
//		logic.operation();
		String result = super.operation();
		
		long endTime = System.currentTimeMillis();
		log.info("TimeDecorator 종료 resultTime = {}ms", (endTime - startTime));
		
		System.out.println(logic.toString());
		System.out.println(super.toString());
		
		return result;
	}
	
}
