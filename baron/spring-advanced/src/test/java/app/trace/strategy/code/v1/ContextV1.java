package app.trace.strategy.code.v1;

import lombok.extern.slf4j.Slf4j;

/**
 * 필드에 전략을 보관하는 방식
 */
@Slf4j
public class ContextV1 {
	private Strategy strategy;
	
	public ContextV1(Strategy strategy) {
		this.strategy = strategy;
	}
	
	public void execute() {
		long startTime = System.currentTimeMillis();
		log.info("logic start");
		
		// 위임된 비즈니스 로직
		this.strategy.call();
		
		long endTime = System.currentTimeMillis();
		log.info("logic end : " + (endTime - startTime));
	}
}
