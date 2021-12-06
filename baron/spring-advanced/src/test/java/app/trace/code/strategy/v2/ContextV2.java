package app.trace.code.strategy.v2;

import lombok.extern.slf4j.Slf4j;

/**
 * 필드에 전략을 보관하는 방식
 */
@Slf4j
public class ContextV2 {
	
	/**
	 * 파라미터로 전달받는 전략패턴
	 * 전략을 필드로 가지지 않는 대신에 항상 파라미털 호출 받는방식
	 * 
	 * @param strategy
	 */
	public void execute(Strategy strategy) {
		long startTime = System.currentTimeMillis();
		log.info("logic start");
		
		// 위임된 비즈니스 로직
		strategy.call();
		
		long endTime = System.currentTimeMillis();
		log.info("logic end : " + (endTime - startTime));
	}
}
