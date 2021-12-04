package app.business.v4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.trace.TraceStatus;
import app.trace.Template.AbstractTemplate;
import app.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {
    private final OrderRepositoryV4 orderRepository;
    private final LogTrace trace;
    
    public void orderItem(String itemId) {
    	AbstractTemplate<Void> template = new AbstractTemplate<Void>(trace) {
			@Override
			protected Void call() {
				orderRepository.save(itemId);
				return null;
			}
		};
    	
		template.execute("OrderServiceV4.orderItem()");
    }
}
