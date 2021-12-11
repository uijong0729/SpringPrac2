package hello.proxy.app.v1;

public class OrderServiceV1Impl implements OrderServiceV1 {

	private final OrderRepositoryV1 repo;
	
	public OrderServiceV1Impl(OrderRepositoryV1 repo) {
		super();
		this.repo = repo;
	}

	@Override
	public void orderItem(String itemId) {
		// TODO Auto-generated method stub
		repo.save(itemId);
	}

}
