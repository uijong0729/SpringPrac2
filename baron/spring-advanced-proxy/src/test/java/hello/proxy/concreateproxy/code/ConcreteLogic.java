package hello.proxy.concreateproxy.code;

import lombok.extern.slf4j.Slf4j;

/**
 * 인터페이스 없는 구체 클래스 기반 프록시
 */
@Slf4j
public class ConcreteLogic {
	public String operation() {
		log.info("ConcreteLogic 실행");
		return "data";
	}
}
