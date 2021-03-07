package com.app.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.login.domain.model.User;
import com.app.login.domain.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	UserService userService;
	
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

}
