package org.scit.www.vo;


public class Customer {
	int customerNumber;
	String id;
	String password;
	String email;
	String regdate;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	
	public Customer(int customerNumber, String id, String password, String email) {
		// TODO Auto-generated constructor stub
		this.customerNumber = customerNumber;
		this.id = id;
		this.password = password;
		this.email = email;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	@Override
	public String toString() {
		return "Customer [customerNumber=" + customerNumber + ", id=" + id + ", password=" + password + ", email="
				+ email + "]";
	}
	
	
	
	
}
