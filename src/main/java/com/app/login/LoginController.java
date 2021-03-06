package com.app.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	
	//ログイン用のGETコントローラー
	@GetMapping("/login")
	public String getLogin(Model model) {
		
		return "login/login";
	}
	
	//ログイン画面のPOSTコントローラー
	@PostMapping("/login")
	public String postLogin(Model model) {
		
		return "/login/login";
	}

}
