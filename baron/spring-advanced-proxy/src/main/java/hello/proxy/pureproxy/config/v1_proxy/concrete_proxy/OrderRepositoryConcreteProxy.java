package hello.proxy.pureproxy.config.v1_proxy.concrete_proxy;

import hello.proxy.pureproxy.app.v2.OrderRepositoryV2;
import hello.proxy.pureproxy.trace.TraceStatus;
import hello.proxy.pureproxy.trace.logtrace.LogTrace;

public class OrderRepositoryConcreteProxy extends OrderRepositoryV2 {
	
	private final OrderRepositoryV2 target;
	private final LogTrace logTrace;
	
	public OrderRepositoryConcreteProxy(OrderRepositoryV2 target, LogTrace logTrace) {
		super();
		this.target = target;
		this.logTrace = logTrace;
	}

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
