package app.trace.threadlocal;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldService {

	private String nameStore;
	
	public String logic(String name) {
		log.info("์ ์ฅ name={}, nameStore={}", name, nameStore);
		nameStore = name;
		sleep(1000);
		log.info("์กฐํ nameStore={}", nameStore);
		return nameStore;
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
