package com.project.hikes.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.hikes.entity.HikeTrail;
import com.project.hikes.entity.HikeUser;
import com.project.hikes.service.UserService;

@Controller
@RequestMapping("/account")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	@Qualifier("hikePasswordAndEmailValidator")
	private Validator validator;


	//add init binder to convert trim input strings
	@InitBinder
	public void initBinder(WebDataBinder dataBinder){

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
		
		//dataBinder.setValidator(validator);

	}


	@GetMapping("/showLoginPage")
	public String showLoginPage(){
		return "users/login";
	}

	//@RequestMapping(value="/authenticateTheUser", method = RequestMethod.POST)
	/*public String processLogin(HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException{
		System.out.println("AUTHENTICATE::: "+request.getParameter("username")+" - "+request.getParameter("password"));

		return "users/a";
	}*/

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		//System.out.println("Now logging user out....");
		//return "/";
		return "redirect:/account/showLoginPage?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}

	//show form to register new user
	@GetMapping("/showRegisterPage")
	public String showRegisterPage(Model model){
		HikeUser user = new HikeUser();
		model.addAttribute("user", user);
		return "users/register";
	}

	//process form to add new trail
	@PostMapping("/registerTheUser")
	public String registerTheUser(@Valid @ModelAttribute("user") HikeUser newUser,
			BindingResult theBindingResult,
			final RedirectAttributes redirectAttrs){
		
		//using special validator for password and email on top of custom
		validator.validate(newUser,theBindingResult);
		
		if(theBindingResult.hasErrors()){
			return "users/register";
		}

		userService.saveUser(newUser);
		redirectAttrs.addFlashAttribute("message", "User successfully registered with email "+newUser
				.getEmail());
		return "redirect:/account/showLoginPage";
		//return "redirect:/hike/home";
	}

}
