package com.app.login.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.login.domain.model.SignupForm;
import com.app.login.domain.model.User;
import com.app.login.domain.service.UserService;

@Controller
public class SignupController {
	
	@Autowired
	private UserService userService;
	
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
	public String getSignUp(@ModelAttribute SignupForm form , Model model) {
		
		//ラジオボタンの初期化メソッド呼び出し
		radioMarriage = initRadioMarriage();
		
		//ラジオボタン用のMapをModelに登録
		model.addAttribute("radioMarriage",radioMarriage);
		
		return "/login/signup";
	}
	
	//ユーザー登録用POSTコントローラー
	//データバインド結果の受け取り
	@PostMapping("/signup")
	public String postSignUp(
			@ModelAttribute @Validated SignupForm form , 
			BindingResult bindingResult ,
			Model model) {
		
		//データバインド失敗
		//入力チェックに引っかかった場合、ユーザー登録画面に戻る
		if(bindingResult.hasErrors()) { return getSignUp(form,model); }
		
		//debug
		//formの中身を確認
		System.out.println(form);
		
		//insert用の変数
		User user = new User();
		
		user.setUserId(form.getUserId());
		user.setPassword(form.getPassword());
		user.setUserName(form.getUserName());
		user.setAge(form.getAge());;
		user.setMarriage(form.isMarriage());
		user.setRole("ROLE_GENERAL");
		
		//User登録処理
		boolean result = userService.insert(user);
		
		//ユーザー登録結果判定
		if(result == true) {
			System.out.println("insert成功");
		}else {
			System.out.println("insert失敗");			
		}
		
		
		return "redirect:/login";
	}
}
