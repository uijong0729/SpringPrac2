package hello.proxy.concreateproxy.code;

public class ConcreteClient {
	private ConcreteLogic concreteLogic;

	public ConcreteClient(ConcreteLogic concreteLogic) {
		super();
		this.concreteLogic = concreteLogic;
	}
	
	public void execute() {
		concreteLogic.operation();
	}
}
