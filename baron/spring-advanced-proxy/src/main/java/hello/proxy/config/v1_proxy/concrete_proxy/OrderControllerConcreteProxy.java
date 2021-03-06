package hello.proxy.config.v1_proxy.concrete_proxy;

import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.trace.TraceStatus;
import hello.trace.logtrace.LogTrace;

public class OrderControllerConcreteProxy extends OrderControllerV2{

	private final OrderControllerV2 target;
	private final LogTrace logTrace;
	
	public OrderControllerConcreteProxy(OrderControllerV2 target, LogTrace logTrace) {
		// 프록시 객체에서는 부모 클래스의 기능을 쓰지 않을 것
		super(null);
		this.target = target;
		this.logTrace = logTrace;
	}

	@Override
	public String request(String itemId) {
		TraceStatus status = null;
		String request = null;
		try {
			status = logTrace.begin("OrderController.request()");
			request = target.request(itemId);
			logTrace.end(status);
			return request;
		} catch (Exception e) {
			// TODO: handle exception
			logTrace.exception(status, e);
			throw e;
		}
	}

	@Override
	public String noLog() {
		// TODO Auto-generated method stub
		return target.noLog();
	}
	
	

}
