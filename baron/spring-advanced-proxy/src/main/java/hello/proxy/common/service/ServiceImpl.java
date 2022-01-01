package hello.proxy.common.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceImpl implements ServiceInterface {

	@Override
	public void save() {
		// TODO Auto-generated method stub
		log.info("save 호출");
	}

	@Override
	public void find() {
		// TODO Auto-generated method stub
		log.info("find 호출");
	}

}
