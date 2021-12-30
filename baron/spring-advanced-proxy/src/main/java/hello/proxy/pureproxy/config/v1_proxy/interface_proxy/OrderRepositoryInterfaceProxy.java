package hello.proxy.pureproxy.config.v1_proxy.interface_proxy;

import hello.proxy.pureproxy.app.v1.OrderRepositoryV1;
import hello.proxy.pureproxy.trace.TraceStatus;
import hello.proxy.pureproxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryInterfaceProxy implements OrderRepositoryV1 {

	private final OrderRepositoryV1 target;
	private final LogTrace logTrace;
	
	@Override
	public void save(String itemId) {
		// TODO Auto-generated method stub
		TraceStatus status = null;
		try {
			status = logTrace.begin("OrderRepository.request()");
			target.save(itemId);
			logTrace.end(status);
		} catch (Exception e) {
			// TODO: handle exception
			logTrace.exception(status, e);
		}
	}

}
