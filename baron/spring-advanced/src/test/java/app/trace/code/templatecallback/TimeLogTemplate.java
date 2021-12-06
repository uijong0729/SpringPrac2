package app.trace.code.templatecallback;

import app.trace.code.strategy.v2.Strategy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeLogTemplate {

	public void execute(Callback callback) {
		long startTime = System.currentTimeMillis();
		log.info("logic start");
		
		// 위임된 비즈니스 로직
		callback.call();
		
		long endTime = System.currentTimeMillis();
		log.info("logic end : " + (endTime - startTime));
	}
}
