package app.trace.Template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubClassLogic1 extends AbstarctTempalte {

	@Override
	protected void call() {
		// TODO Auto-generated method stub
		log.info("business logic 1");
	}

}
