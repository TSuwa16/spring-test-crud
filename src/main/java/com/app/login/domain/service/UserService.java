package com.app.login.domain.service;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
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
	
	public void userCsvOut() throws DataAccessException{
		
		dao.userCsvOut();
	}
	
	public byte[] getFile(String fileName) throws IOException {
		
		FileSystem fs = FileSystems.getDefault();
		
		Path p = fs.getPath(fileName);
		
		byte[] bytes = Files.readAllBytes(p);
		
		return bytes;
	}
	
}
