package app.trace.code.templatecallback;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TemplateCallbackTest {

	@Test
	void testCallback() {
		TimeLogTemplate tt = new TimeLogTemplate();
		tt.execute(() -> {
			log.info("logic");
		});
	}
}
