package app.trace.threadlocal;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalService {

	// 쓰레드 로컬을 모두 사용하면 ThreadLocal.remove()를 호출해서 쓰레드 로컬에 저장된 값을 제거해주어야 한다.
	private ThreadLocal<String> nameStore = new ThreadLocal<>();
	
	public String logic(String name) {
		log.info("저장 name={}, nameStore={}", name, nameStore.get());
		nameStore.set(name);
		sleep(1000);
		log.info("조회 nameStore={}", nameStore.get());
		return nameStore.get();
	}
	
	public static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
