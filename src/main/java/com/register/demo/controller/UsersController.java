package com.register.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.register.demo.model.Roles;
import com.register.demo.model.Users;
import com.register.demo.service.UsersService;

@Controller
public class UsersController {
	@Autowired
	private UsersService usersService;
	@RequestMapping(value= {"/","/login"},method=RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();
		model.setViewName("users/login");
		return model;
	}
	@RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
	 public ModelAndView signup() {
	  ModelAndView model = new ModelAndView();
	  Users users = new Users();
	  model.addObject("users", users);
	  model.setViewName("users/signup");
	  
	  return model;
	 }
	@RequestMapping(value= {"/signup"},method=RequestMethod.POST)
	public ModelAndView createUser(@Valid Users users,BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		Users usersExists = usersService.findUsersByEmail(users.getEmail());
		if(usersExists != null) {
			bindingResult.rejectValue("email","error.users","This email already exist!");
			
		}
		if(bindingResult.hasErrors()) {
			model.setViewName("users/signup");
		}
		else {
			usersService.saveUser(users);
			model.addObject("msg","User has ben registered Succesfully!");
			model.addObject("users",new Users());
			model.setViewName("users/signup");
		}
		return model;
		}
	@RequestMapping(value= {"/home/home"},method=RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Users users = usersService.findUsersByEmail(auth.getName());
		//Users usersroles = usersService.findUsersByRoles_id(auth.getName());
		model.addObject("usersName",users.getFirstname()+""+users.getLastname());
		//model.addObject("usersName",usersroles.getRoles());
		//Users r_1 = usersService.findUsersByRoles_id("admin");
		//Users r_2 = usersService.findUsersByRoles_id("yetki1");
		//Users r_3 = usersService.findUsersByRoles_id("yetki2");
		System.out.println("users ="+users);
		//System.out.println("users roles ="+usersroles);
		System.out.println(users.getFirstname());
		System.out.println(users.getLastname());
		System.out.println(users.getEmail());
		System.out.println(users.getPw());
		System.out.println(users.getActives());
		System.out.println(users.getRoles());
		
		if(users.getFirstname().equals("yetkibir")) {
			model.setViewName("home/yetki1");
			return model;
		}
		else {
		model.setViewName("home/yetki2");
		return model;
	}
		
	}
	@RequestMapping(value= {"/access_denied"},method=RequestMethod.GET)
	public ModelAndView accessDenied() {
	ModelAndView model = new ModelAndView();
	model.setViewName("error/access_denied");
	return model;
	}
}