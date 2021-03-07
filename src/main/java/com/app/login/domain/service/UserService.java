package com.app.login.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.login.domain.model.User;
import com.app.login.domain.repository.UserDao;

@Service
public class UserService {

	@Autowired
	UserDao dao;
	
	//insert
	public boolean insert(User user) {
		
		//insert実行
		int rowNumber = dao.insertOne(user);
		
		//判定用変数
		boolean result = false;
		
		if(rowNumber > 0 ) {
			//insert成功
			result = true;
		}
		
		return result;
		
	}
	
	//count
	public int count() {
		return dao.count();
	}
	
	//select All
	public List<User> selectMany(){
		return dao.selectMany();
	}
}
