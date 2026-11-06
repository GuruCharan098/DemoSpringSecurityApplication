package com.example.demoSpringSecureApp.ControllerApp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	   //Home Page
	     @GetMapping("/")
	     public String home() {
	    	 return "home";
	     }
	     
	   //Login Page
	     @GetMapping("/login")
	     public String login() {
	    	 return "login";
	     }
	     
	   // Admin-Dashboard
	     @GetMapping("/admin/admindashboard")
	     public String adminDashboard() {
	    	 return "admin-dashboard";
	     }
	     
	  // Student-Dashboard
	     @GetMapping("/student/studentdashboard")
	     public String studentDashboard() {
	    	 return "student-dashboard";
	     }
	     
	  // Access Denied Page: 
	     @GetMapping("/access-denied")
	     public String accessDenied() {
	    	 return "access-denied";
	     }
}
