package app.business.v5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.trace.TraceStatus;
import app.trace.Template.AbstractTemplate;
import app.trace.callback.TraceTemplate;
import app.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@Service
public class OrderServiceV5 {
    private final OrderRepositoryV5 orderRepository;
    TraceTemplate template;
    
    @Autowired
    public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace trace) {
		super();
		this.orderRepository = orderRepository;
		this.template = new TraceTemplate(trace);
	}

    
    public void orderItem(String itemId) {
		template.execute("OrderServiceV5.orderItem()", () -> {
			orderRepository.save(itemId);
			return null;
		});
    }
}
