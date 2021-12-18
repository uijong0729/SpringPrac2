package hello.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealSubject implements Subject {

	/**
	 *	데이터 조회에 1초가 걸린다 가정
	 */
	@Override
	public String operation() {
		// TODO Auto-generated method stub
		log.info("실제 객체 호출");
		sleep(1000);
		return "data";
	}

	private void sleep(int i) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(i);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
