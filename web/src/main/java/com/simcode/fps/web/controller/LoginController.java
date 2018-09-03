package com.simcode.fps.web.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.simcode.fps.repository.model.Role;
import com.simcode.fps.repository.model.Student;
import com.simcode.fps.repository.model.User;
import com.simcode.fps.service.impl.LoginService;
import com.simcode.fps.service.impl.StudentService;
import com.simcode.fps.web.dto.LoginUserDetails;
import com.simcode.fps.web.dto.UserDto;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private StudentService studentService;
	

	@GetMapping("/")
    public String home1() {
        return "/home";
    }
	
	@GetMapping("/home")
	public String home(Model model) {
		List<Student> studentsWithDues = studentService.findStudentsWithDues();
		model.addAttribute("students", studentsWithDues);
		return "home";
	}
	
	@PostMapping("/home")
	public String doHome() {
		return "home";
	}
	
	@GetMapping("/login")
	public String login(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		model.addAttribute("loginForm", new UserDto());
		return "login";
	}
	
	@PostMapping("/login")
	public String doLogin() {
		return "home";
	}
	
	@GetMapping("/login?logout")
	public String logout() {
		return "login";
	}
	
	@GetMapping("/login?error")
	public String loginError() {
		return "login";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}
	
	@GetMapping("/403")
	public String errorPage() {
		return "/error/403";
	}
	
	@GetMapping("/error")
	public String error() {
		return "error/403";
	}
	
	@GetMapping("/profile")
	public String profile(Model model, BCryptPasswordEncoder bCryptPasswordEncoder) {
		
		UserDto userDto =new UserDto();
	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof UsernamePasswordAuthenticationToken) {
			LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
			userDto =new UserDto(loginUserDetails.getUser());
		}
		
		model.addAttribute("userDto", userDto);
		return "profile";
	}

	@PostMapping("/signup")
	public String registerUser(UserDto userdto, BindingResult result, BCryptPasswordEncoder encoder) {
		if (loginService.checkIfEmailExists(userdto.getEmail())) {
			result.rejectValue("emailId", "400", "Email Id already exists");
			return "signup";
		}
		
		User user = new User(userdto.getUsername(), encoder.encode(userdto.getPassword()), userdto.getEmail());
		Role role = new Role("ROLE_USER");
		role.setUser(user);
		user.setRoles(Arrays.asList(role)); //TODO: all have user roles
		loginService.registerUser(user);
		return "redirect:/login";
	}

	
	@PostMapping("/updateUserProfile")
	public String updateUserProfile(Authentication authentication, UserDto userdto, BindingResult result, BCryptPasswordEncoder encoder,
			HttpSession httpSession) {
		if (!(authentication instanceof UsernamePasswordAuthenticationToken))
			return "/403";

		User user = loginService.findByUserName(userdto.getUsername());
		String password;
		if (encoder.matches(userdto.getPassword(), user.getPassword())) {
			password = userdto.getPassword();
		} else {
			password = encoder.encode(userdto.getPassword());
		}
		user.setPassword(password);
		user.setEmail(userdto.getEmail());
		
		loginService.registerUser(user);
		
		httpSession.invalidate();
		return "redirect:/login";
	}
}
