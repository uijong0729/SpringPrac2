package app.trace.code.strategy.v1;

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
public class ContextV1Test {
	
	/**
	 *	전략패턴 적용 코드
	 *	- 람다식 : 인터페이스에 메서드가 1개만 있을경우 람다식 사용가능
	 */
	@Test
	void strategyV4() {
		ContextV1 context1 = new ContextV1(() -> {
			log.info("logic 1");
		});
		context1.execute();
		
		ContextV1 context2 = new ContextV1(() -> {
				log.info("logic 2");
		});
		context2.execute();
	}
	
	/**
	 *	전략패턴 적용 코드 : 익명 내부클래스
	 */
	@Test
	void strategyV3() {
		ContextV1 context1 = new ContextV1(new Strategy() {
			@Override
			public void call() {
				// TODO Auto-generated method stub
				log.info("logic 1");
			}
		});
		context1.execute();
		
		ContextV1 context2 = new ContextV1(new Strategy() {
			@Override
			public void call() {
				// TODO Auto-generated method stub
				log.info("logic 2");
			}
		});
		context2.execute();
	}
	
	/**
	 *	전략패턴 적용 코드 : 익명 내부클래스
	 */
	@Test
	void strategyV2() {
		Strategy strategyLogic1 = new Strategy() {
			@Override
			public void call() {
				// TODO Auto-generated method stub
				log.info("logic 1");
			}
		};
		
		ContextV1 context1 = new ContextV1(strategyLogic1);
		context1.execute();
		
		
		Strategy strategyLogic2 = new Strategy() {
			@Override
			public void call() {
				// TODO Auto-generated method stub
				log.info("logic 2");
			}
		};
		
		ContextV1 context2 = new ContextV1(strategyLogic2);
		context2.execute();
	}
	
	/**
	 *	전략패턴 적용 코드 
	 */
	@Test
	void strategyV1() {
		StrategyLogic1 logic1 = new StrategyLogic1();
		ContextV1 context1 = new ContextV1(logic1);
		context1.execute();
		
		StrategyLogic2 logic2 = new StrategyLogic2();
		ContextV1 context2 = new ContextV1(logic2);
		context2.execute();
	}
	
	/**
	 *	전략패턴 미적용 코드 
	 */
	@Test
	void strategyV0() {
		logic1();
		logic2();
	}

	void logic1() {
		long startTime = System.currentTimeMillis();
		log.info("logic start");
		
		log.info("logic processing 1 : asdf");
		
		long endTime = System.currentTimeMillis();
		log.info("logic end : " + (endTime - startTime));
	}
	
	void logic2() {
		long startTime = System.currentTimeMillis();
		log.info("logic start");
		
		log.info("logic processing 2 : qwer");
		
		long endTime = System.currentTimeMillis();
		log.info("logic end : " + (endTime - startTime));
	}
}
