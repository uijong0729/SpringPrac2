package app.trace.code.strategy.v2;

import org.junit.jupiter.api.Test;

import app.trace.code.template.AbstarctTempalte;
import lombok.extern.slf4j.Slf4j;

/**
 * 전략 패턴
 *  - 변하지 않는 부분은 Context라는 곳에 두고
 *  - 변하는 부분을 Strategy라는 인터페이스를 만들고 해당 인터페이스를 구현하도록 문제를 해결
 *  - 상속이 아니라 위임으로 문제를 해결하는 것
 *	- 알고리즘 제품군을 정의하고 각각 캡슐화하여 상호 교환 가능하게 만들자
 */
@Slf4j
public class ContextV2Test {
	
	/**
	 *	실행 할 때마다 전략을 유연하게 변경할 수 있는 장점이 있음
	 *	실행 할 때마다 전략을 계속 지정해줘야 한다는 단점이 있음
	 */
	@Test
	void strategy2() {
		ContextV2 context = new ContextV2();
		context.execute(() -> log.info("logic 1"));
		
		ContextV2 context2 = new ContextV2();
		context2.execute(() -> {
			log.info("logic 2-1");
			log.info("logic 2-2");
		});
	}
	
	@Test
	void strategy1() {
		ContextV2 context = new ContextV2();
		context.execute(new Strategy() {
			@Override
			public void call() {
				log.info("logic 1");
			}
		});
	}
}
