package com.app.login;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {
	
	//ラジオボタンの実装
	public Map<String,String> radioMarriage;
	
	//ラジオボタン初期化メソッド
	private Map<String,String> initRadioMarriage(){
		
		Map<String,String> radio = new LinkedHashMap<>();
		
		//既婚、未婚を格納
		radio.put("既婚", "true");
		radio.put("未婚","false");
		
		return radio;
	}
	
	//ユーザー登録画面のGETコントローラー
	@GetMapping("/signup")
	public String getSignUp(Model model) {
		
		//ラジオボタンの初期化メソッド呼び出し
		radioMarriage = initRadioMarriage();
		
		//ラジオボタン用のMapをModelに登録
		model.addAttribute("radioMarriage",radioMarriage);
		
		return "/login/signup";
	}
	
	//ユーザー登録用POSTコントローラー
	@PostMapping("/signup")
	public String postSignUp(Model model) {
		
		return "redirect:/login";
	}
}
