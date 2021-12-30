package hello.proxy.pureproxy.app.v3;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceV3 {
	
	private final OrderRepositoryV3 repo;
	
	public OrderServiceV3(OrderRepositoryV3 orderRepositoryV3) {
		super();
		this.repo = orderRepositoryV3;
	}

	public void orderItem(String itemId) {
		// TODO Auto-generated method stub
		repo.save(itemId);
	}
}
