package app.trace.code.template;

import lombok.extern.slf4j.Slf4j;

/**
 * 템플릿 메서드 패턴 참고용 
 * 
 * @author kroch
 *
 */
@Slf4j
public abstract class AbstarctTempalte {
	
	public void execute() {
		// 공통 처리 영역
		long startTime = System.currentTimeMillis();
		log.info("logic start");
		
		// 비즈니스 로직 부분은 상속으로 구현
		call();
		
		// 공통 처리 영역		
		long endTime = System.currentTimeMillis();
		log.info("logic end : " + (endTime - startTime));
	}
	
	abstract protected void call();
}
