package app.trace.code.template;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TemplateMethodTest {

	/**
	 *	클래스 SubClassLogic1, SubClassLogic2를 정의한 다음 사용하는 경우
	 */
	@Test
	void templateMethod() {
		AbstarctTempalte template1 = new SubClassLogic1();
		template1.execute();
		
		AbstarctTempalte template2 = new SubClassLogic2();
		template2.execute();
	}
	
	/**
	 *	익명 내부 클래스를 사용하는 경우
	 */
	@Test
	void templateMethod2() {
		AbstarctTempalte template1 = new AbstarctTempalte() {
			@Override
			protected void call() {
				log.info("logic processing 1 : asdf");
				log.info(this.getClass().getName());	// 익명 내부 클래스 참고용 로그
			}
		};
		template1.execute();
		
		new AbstarctTempalte() {
			@Override
			protected void call() {
				log.info("logic processing 2 : qwer");
				log.info(this.getClass().getName());	// 익명 내부 클래스 참고용 로그
			}
		}.execute();
	}

	
//  아래는 템플릿 메서드 패턴을 적용하지 않았을 때 작성된 코드	
//
//	void logic1() {
//		long startTime = System.currentTimeMillis();
//		log.info("logic start");
//		
//		log.info("logic processing 1 : asdf");
//		
//		long endTime = System.currentTimeMillis();
//		log.info("logic end : " + (endTime - startTime));
//	}
//	
//	void logic2() {
//		long startTime = System.currentTimeMillis();
//		log.info("logic start");
//		
//		log.info("logic processing 2 : qwer");
//		
//		long endTime = System.currentTimeMillis();
//		log.info("logic end : " + (endTime - startTime));
//	}
}
