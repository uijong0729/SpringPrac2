package app.trace.threadlocal;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalServiceTest {

	private ThreadLocalService service = new ThreadLocalService();
	
	/**
	 *	동시성 문제는 지역 변수에서는 발생하지 않는다.
	 *	동시성 문제는 여러 쓰레드가 같은 인스턴스의 필드에 접근하기 때문에 발생한다. 
	 *	(싱글톤 패턴의 객체에서 자주 보이는 문제) 
	 */
	@Test
	final void threadLocal() {
		log.info("main start");
		Thread threadA = new Thread(() -> {
			// runnable.run()
			service.logic("userA");
		});
		threadA.setName("Thread-A");
		
		Thread threadB = new Thread(() -> {
			// runnable.run()
			service.logic("userB");
		});
		threadB.setName("Thread-B");
		
		threadA.start();
		//FieldService.sleep(2000);	// 동시성 문제 발생 X
		ThreadLocalService.sleep(100);	// 동시성 문제 발생 O
		threadB.start();
		
		try {
			threadB.join(); // 쓰레드 종료 대기
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("main exit");
		
	}
}
