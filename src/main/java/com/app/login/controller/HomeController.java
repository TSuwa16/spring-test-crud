package com.app.login.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.login.domain.model.SignupForm;
import com.app.login.domain.model.User;
import com.app.login.domain.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	UserService userService;
	
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
	
	//ホーム画面のGETメソッド
	@GetMapping("/home")
	public String getHome(Model model) {
		
		//コンテンツ部分にホーム画面を表示させるための文字列を登録する
		//
		model.addAttribute("contents" , "login/home :: home_contents");
		
		return "login/homeLayout";
	}
	
	//ユーザー一覧表示メソッド
	@GetMapping("/userList")
	public String getUserList(Model model) {
		
		model.addAttribute("contents" , "login/userList :: userList_contents");
		
		//ユーザー一覧を取得
		List<User> userList = userService.selectMany();
		model.addAttribute("userList" , userList);
		
		//データ件数を取得
		int count = userService.count();
		model.addAttribute("userListCount" , count);
		
		return "login/homeLayout";
		
	}
	
	
	//ログアウト処理
	@PostMapping("/logout")
	public String postLogout() {
		
		return "redirect:/login";
	}
	
	//ユーザー一覧CSV出力
	@GetMapping("/userList/csv")
	public String getUserListCsv(Model model) {
		
		//TODO CSV出力処理
		return getUserList(model);
		
	}
	
	//ユーザ詳細画面表示
	@GetMapping("userDetail/{id:.+}")
	public String getUserList(
			@ModelAttribute SignupForm form,
			Model model,
			@PathVariable("id") String userId) {
		
		//debug
		System.out.println("userId=" + userId);
		
		//コンテンツ部にユーザ詳細情報を表示
		model.addAttribute("contents" , "login/userDetail :: userDetail_contents");
		
		//結婚ステータス用ラジオボタンの初期化
		radioMarriage = initRadioMarriage();
		
		model.addAttribute("radioMarriage",radioMarriage);
		
		if(userId != null && userId.length() > 0) {
			
			User user = userService.selectOne(userId);
			
			form.setUserId(user.getUserId());
			form.setPassword(user.getPassword());
			form.setUserName(user.getUserName());
			form.setBirthday(user.getBirthday());
			form.setAge(user.getAge());
			form.setMarriage(user.isMarriage());
			
			model.addAttribute("signupForm" , form);
		}
		
		return "login/homeLayout";
	}
	
	//ユーザー更新処理
	@PostMapping(value = "/userDetail" , params = "update")
	public String postUserDetailUpdate(
			@ModelAttribute SignupForm form ,
			Model model) {
		
		//debug
		System.out.println("更新ボタンの処理");
		
		//Userインスタンスの生成
		User user = new User();
		
		user.setUserId(form.getUserId());
		user.setPassword(form.getPassword());
		user.setUserName(form.getUserName());
		user.setBirthday(form.getBirthday());
		user.setAge(form.getAge());
		user.setMarriage(form.isMarriage());
		
		boolean result = userService.updateOne(user);
		
		if(result == true) {
			model.addAttribute("result" , "更新成功");
		}else {
			model.addAttribute("result" , "更新失敗");
		}
		
		return getUserList(model);
	}

}
