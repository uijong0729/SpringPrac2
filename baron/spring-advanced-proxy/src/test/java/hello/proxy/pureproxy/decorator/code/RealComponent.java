package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealComponent implements Component {

	@Override
	public String operation() {
		// TODO Auto-generated method stub
		log.info("real component 실행");
		return "data";
	}

}
