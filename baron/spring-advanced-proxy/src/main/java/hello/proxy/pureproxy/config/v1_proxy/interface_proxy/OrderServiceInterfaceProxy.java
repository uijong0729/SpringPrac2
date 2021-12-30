package hello.proxy.pureproxy.config.v1_proxy.interface_proxy;

import hello.proxy.pureproxy.app.v1.OrderRepositoryV1;
import hello.proxy.pureproxy.app.v1.OrderServiceV1;
import hello.proxy.pureproxy.trace.TraceStatus;
import hello.proxy.pureproxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderServiceInterfaceProxy implements OrderServiceV1 {

	private final OrderServiceV1 target;
	private final LogTrace logTrace;
	
	@Override
	public void orderItem(String itemId) {
		TraceStatus status = null;
		try {
			status = logTrace.begin("OrderService.orderItem()");
			target.orderItem(itemId);
			logTrace.end(status);
		} catch (Exception e) {
			// TODO: handle exception
			logTrace.exception(status, e);
		}
	}
}
