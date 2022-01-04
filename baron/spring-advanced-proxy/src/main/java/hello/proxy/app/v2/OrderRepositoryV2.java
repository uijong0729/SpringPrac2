package hello.proxy.app.v2;

public class OrderRepositoryV2{

	public void save(String itemId) {
		// TODO Auto-generated method stub
		if (itemId.equals("ex")) {
			throw new IllegalStateException("예외 발생");
		}
		sleep(1000);
	}

	private void sleep(int i) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(i);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
