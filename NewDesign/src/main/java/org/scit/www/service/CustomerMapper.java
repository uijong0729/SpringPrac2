package org.scit.www.service;

import java.util.HashMap;

import org.scit.www.vo.Customer;

public interface CustomerMapper {
	public int insertCustomer(Customer customer);
	public String findCustomer(String id);
	public String findEmail(String id);
	public Customer getLogin(Customer customer);
	//================================//
	public Customer getCustomer(String id);
	public int updateCustomer(HashMap<String, String> map);
	public int updateCustomer2(HashMap<String, String> map);
	public int deleteCustomer(Customer customer);
	
}

