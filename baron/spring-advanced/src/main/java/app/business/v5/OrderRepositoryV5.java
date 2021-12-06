package app.business.v5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.trace.TraceStatus;
import app.trace.Template.AbstractTemplate;
import app.trace.callback.TraceTemplate;
import app.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@Repository
public class OrderRepositoryV5 {
	TraceTemplate template;
    
    @Autowired
    public OrderRepositoryV5(LogTrace trace) {
		this.template = new TraceTemplate(trace);
	}

    
	public void save(String itemId) {
		template.execute("OrderRepositoryV5.save()" , () -> {
			if (itemId.equals("ex")) {
				throw new IllegalStateException("error");
			}
			sleep(1000);	
			return null;
		});
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
