package hello.proxy.config.v1_proxy.interface_proxy;

import hello.proxy.app.v1.OrderControllerV1;
import hello.trace.TraceStatus;
import hello.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderControllerInterfaceProxy implements OrderControllerV1{

	private final OrderControllerV1 target;
	private final LogTrace logTrace;
	
	@Override
	public String request(String itemId) {
		TraceStatus status = null;
		String request = null;
		try {
			status = logTrace.begin("OrderController.request()");
			request = target.request(itemId);
			logTrace.end(status);
		} catch (Exception e) {
			// TODO: handle exception
			logTrace.exception(status, e);
		}
		return request;
	}

	@Override
	public String noLog() {
		return target.noLog();
	}
	
}
