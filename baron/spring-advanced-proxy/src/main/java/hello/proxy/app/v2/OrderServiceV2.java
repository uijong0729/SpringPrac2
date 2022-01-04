package hello.proxy.app.v2;

public class OrderServiceV2 {

	private final OrderRepositoryV2 repo;
	
	public OrderServiceV2(OrderRepositoryV2 repo) {
		super();
		this.repo = repo;
	}

	public void orderItem(String itemId) {
		// TODO Auto-generated method stub
		repo.save(itemId);
	}
}
