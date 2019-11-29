package com.lifesup.controller;



import java.util.List;

import com.lifesup.dao.UserDAO;
import com.lifesup.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class UserController {
	
    UserDAO s  = new UserDAO();
    @RequestMapping(value="/", method= RequestMethod.GET)
    public ModelAndView  index() {
		 ModelAndView modelAndView = new ModelAndView("index");
		    return modelAndView;
    }
    
	@RequestMapping(value="/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	  public List<User> fillAll() {
	    return s.showAll();
	  }
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable("id") int id) {
		s.delete(id);
		return "Ban vua xoa" +id;
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public User addUser(@RequestBody User user) {
		s.save(user);
		return user;
		
	}
	
	@RequestMapping(value ="/update", method = RequestMethod.PUT)
	public User updateUser(@RequestBody User user) {
		s.update(user);
		return user;
	}
}
