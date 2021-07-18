package com.example.demo.singleton;

public class SingletonService {
	
	/**
	 * static영역에 instance를 미리 하나 생성해서 올려둔다
	 * 가장 단순하고 안전한 방법이지만 몇 가지 단점이 있음
	 * 	1. 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다
	 * 	2. 클라이언트가 구체화된 클래스에 의존한다.
	 * 	3. 인스턴스가 미리 만들어지기 때문에 인스턴스의 속성에 대해 유연하게 테스트하기 어렵다
	 * 	4. private생성자를 사용하면 상속받는 클래스를 만들기 어렵다
	 */
	private static final SingletonService instance = new SingletonService();
	
	/**
	 * new를 통해 생성하지 못하도록 제어 
	 */
	private SingletonService() {}
	
	/**
	 * <p>SingletonService의 인스턴스가 필요할 땐 getInstance메소드를 호출한다.</p>
	 * 
	 * @return SingletonService
	 */
	public static SingletonService getInstance() {
		return instance;
	}
	
	public void logic() {
		System.out.println("싱글톤 객체 로직 호출");
	}
}
