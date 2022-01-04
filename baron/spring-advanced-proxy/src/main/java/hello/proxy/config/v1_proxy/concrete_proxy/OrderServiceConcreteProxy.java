package hello.proxy.config.v1_proxy.concrete_proxy;

import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.trace.TraceStatus;
import hello.trace.logtrace.LogTrace;

public class OrderServiceConcreteProxy extends OrderServiceV2 {
	
	private final OrderServiceV2 target;
	private final LogTrace logTrace;
	
	public OrderServiceConcreteProxy(OrderServiceV2 target, LogTrace logTrace) {
		// 컴파일상 필요하기 때문에 호출 다만 Repository에 의존하는 부분은 프록시에서 다루기 때문에 null로 넘김
		super(null);
		this.target = target;
		this.logTrace = logTrace;
	}

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
