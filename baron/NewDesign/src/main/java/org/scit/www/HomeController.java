package org.scit.www;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.scit.www.dao.customerDAO;
import org.scit.www.fileIO.ExcelFile;
import org.scit.www.vo.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	public static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Inject
	customerDAO dao;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
	public String home(Locale locale, Model model) {
		
		
		return "home";
	}
	
	
	@RequestMapping(value="/signin", method=RequestMethod.GET)
	public String signin()
	{
		return "CusPage/login";
	}
	
	@RequestMapping(value="/joinus", method=RequestMethod.GET)
	public String joinus()
	{
		return "CusPage/agreement";
	}
	

	@RequestMapping(value="/agree", method=RequestMethod.GET)
	public String agree()
	{
		return "CusPage/joinus";
	}
	
	
	@RequestMapping(value="/tryjoin", method=RequestMethod.POST)
	public String gojoin(Customer customer)
	{
		System.out.println(customer);
		System.out.println(dao.insertCustomer(customer));
		
		return "redirect:/";
	}
	
	@ResponseBody
	@RequestMapping(value="/isThereId", method=RequestMethod.POST)
	public String isThereId(String id)
	{
		String result = dao.findCustomer(id);
		
		if(id.equals(result))
		{
			return id;
		}
		else
		{
			return "";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/isThereEmail", method=RequestMethod.GET)
	public String isThereEmail(String email)
	{
		String result = dao.findEmail(email);
		
		if(email.equals(result))
		{
			return email;
		}
		else
		{
			return "";
		}
	}
	
	@RequestMapping(value="/loginButton", method=RequestMethod.POST)
	public String loginform(Customer customer, Model model, HttpSession session)
	{
		Customer cus = dao.getLogin(customer);
		if(cus == null)
		{
			model.addAttribute("loginResult", 1);
			return "CusPage/login";
		}
		else
		{	
			model.addAttribute("loginResult", 0);
			session.setAttribute("loginId", cus.getId());
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/goLogout", method=RequestMethod.GET)
	public String goLogout(HttpSession session)
	{
		session.removeAttribute("loginId");
		return "redirect:/";
	}
	
	@RequestMapping(value="/testlink", method=RequestMethod.GET)
	public String testlink()
	{
		return "backup";
	}
	
	@RequestMapping(value="/goMypage", method=RequestMethod.GET)
	public String goMypage(HttpSession session, Model model) {
		
		String id = (String)session.getAttribute("loginId");
		
		Customer cus = dao.getCustomer(id);
		
		model.addAttribute("myInfo", cus);
			
		return "CusPage/goMypage";
	}
	
	
	@RequestMapping(value="/deleteCustomer", method=RequestMethod.POST)
	public String goAway(HttpSession session, String id) {
		session.removeAttribute("loginId");
		System.out.println(dao.deleteCustomer(dao.getCustomer(id)));
		
		return "redirect:/";
	}
	
	
	@RequestMapping(value="/goAway", method=RequestMethod.GET)
	public String goAway(Model model, HttpSession session) {
		
		String getId = (String)session.getAttribute("loginId");
		
		model.addAttribute("pass", dao.getCustomer(getId).getPassword());
		
		return "CusPage/goAway";
	}
	
	@ResponseBody
	@RequestMapping(value="/updateCustomer", method=RequestMethod.POST)
	public HashMap<String, String> updateCustomer(String customerNumber ,String id, String password, String email) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		if (password == null || password == "")
		{
			map.put("id", id);
			map.put("email", email);
			map.put("customerNumber", customerNumber);
			System.out.println(dao.updateCustomer2(map));
			
		}
		else
		{

			map.put("id", id);
			map.put("email", email);
			map.put("customerNumber", customerNumber);
			map.put("password", password);
			System.out.println(dao.updateCustomer(map));
		}
		
		
		
		return map;
	}
	
	
	
}
