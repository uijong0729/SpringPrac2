package app.business.v4;

import org.springframework.stereotype.Repository;

import app.trace.TraceStatus;
import app.trace.Template.AbstractTemplate;
import app.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {
	private final LogTrace trace;

	public void save(String itemId) {
    	AbstractTemplate<Void> template = new AbstractTemplate<Void>(trace) {
			@Override
			protected Void call() {
				if (itemId.equals("ex")) {
					throw new IllegalStateException("error");
				}
				sleep(1000);	
				return null;
			}
		};
		
		template.execute("OrderRepositoryV4.save()");
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
