package com.app.login.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.app.login.domain.model.User;
import com.app.login.domain.repository.UserDao;

@Service
public class UserService {
	
	@Autowired
	@Qualifier("UserDaoNamedJdbcImpl")
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
	
	//件数取得
	public int count() {
		return dao.count();
	}
	
	//全件取得
	public List<User> selectMany(){
		return dao.selectMany();
	}
	
	//1件取得
	public User selectOne(String userId) {
		return dao.selectOne(userId);
	}
	
	//1件更新
	public boolean updateOne(User user) {
		
		int rowNumber = dao.updateOne(user);
		
		boolean result = false ;
		
		if(rowNumber > 0 ) {
			return true;
		}
		
		return result;
	}
	
	public boolean deleteOne(String userId) {
		
		int rowNumber = dao.deleteOne(userId);
		
		boolean result = false ;
		
		if(rowNumber > 0) { result = true ;}
		
		return result;
	}
	
}
