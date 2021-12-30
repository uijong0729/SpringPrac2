package hello.proxy.pureproxy.app.v1;

public class OrderControllerV1Impl implements OrderControllerV1{

	private final OrderServiceV1 service;
	
	public OrderControllerV1Impl(OrderServiceV1 service) {
		super();
		this.service = service;
	}

	@Override
	public String request(String itemId) {
		// TODO Auto-generated method stub
		service.orderItem(itemId);
		return "ok";
	}

	@Override
	public String noLog() {
		// TODO Auto-generated method stub
		return "ok";
	}

}
