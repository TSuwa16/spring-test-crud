package com.app.login;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.login.domain.model.SignupForm;

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
		
		return "redirect:/login";
	}
}
