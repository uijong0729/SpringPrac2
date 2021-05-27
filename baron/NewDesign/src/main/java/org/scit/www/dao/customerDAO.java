package org.scit.www.dao;

import java.util.HashMap;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.scit.www.service.CustomerMapper;
import org.scit.www.vo.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class customerDAO implements CustomerMapper{
	
	@Inject
	SqlSession session;

	@Override
	public int insertCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return session.getMapper(CustomerMapper.class).insertCustomer(customer);
	}


	@Override
	public String findCustomer(String id) {
		// TODO Auto-generated method stub
		return session.getMapper(CustomerMapper.class).findCustomer(id);
	}

	@Override
	public String findEmail(String id) {
		// TODO Auto-generated method stub
		return session.getMapper(CustomerMapper.class).findEmail(id);
	}

	@Override
	public Customer getLogin(Customer customer) {
		// TODO Auto-generated method stub
		return session.getMapper(CustomerMapper.class).getLogin(customer);
	}

	@Override
	public Customer getCustomer(String id) {
		// TODO Auto-generated method stub
		return session.getMapper(CustomerMapper.class).getCustomer(id);
	}

	@Override
	public int updateCustomer(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return session.getMapper(CustomerMapper.class).updateCustomer(map);
	}
	
	@Override
	public int updateCustomer2(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return session.getMapper(CustomerMapper.class).updateCustomer2(map);
	}

	@Override
	public int deleteCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return session.getMapper(CustomerMapper.class).deleteCustomer(customer);
	}
	
	
	
}
