package org.eialid.springsecurity.joy.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppLogIn {

	@GetMapping("/")
	public String showHomePage() {
		
		return "homePage";
	}
	
	@GetMapping("/managers")
	public String showLeadersPage() {
		return "managersPage";
	}
	
	@GetMapping("/admins")
	public String showAdminsPage() {
		return "adminsPage";
	}
	
	@GetMapping("/access-denied")
	public String accessDenialPage() {
		return "denialPage";
	}
	
}
