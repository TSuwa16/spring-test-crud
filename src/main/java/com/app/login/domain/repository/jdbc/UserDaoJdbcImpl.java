package com.app.login.domain.repository.jdbc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.login.domain.model.User;
import com.app.login.domain.repository.UserDao;

@Repository
public class UserDaoJdbcImpl implements UserDao{
	
	@Autowired
	JdbcTemplate jdbc;
	
	//件数取得
	@Override
	public int count() throws DataAccessException {
		
		//全件数取得してカウント
		int count = jdbc.queryForObject("SELECT COUNT(*) FROM m_user", Integer.class);
		
		return count;
	}
	
	//Userテーブルにデータを1件追加
	@Override
	public int insertOne(User user) throws DataAccessException {
		
		int rowNumber = jdbc.update("INSERT INTO m_user("
				+ "user_id ,"
				+ "password ,"
				+ "user_name ,"
				+ "birthday ,"
				+ "age ,"
				+ "marriage ,"
				+ "role)"
				+ "VALUES(?,?,?,?,?,?,?)"
				,user.getUserId()
				,user.getPassword()
				,user.getUserName()
				,user.getBirthday()
				,user.getAge()
				,user.isMarriage()
				,user.getRole());
		
		return rowNumber;
	}
	
	//Userテーブルからデータを1件取得
	@Override
	public User selectOne(String userId) throws DataAccessException {
		
		Map<String,Object> map = jdbc.queryForMap(
				"SELECT * FROM m_user "
				+ "WHERE user_id = ?"
				,userId);
		
		User user = new User();
		
		user.setUserId((String)map.get("user_id"));
		user.setPassword((String)map.get("password"));
		user.setUserName((String)map.get("user_name"));
		user.setBirthday((Date)map.get("birthday"));
		user.setAge((int)map.get("age"));
		user.setMarriage((Boolean)map.get("marriage"));
		user.setRole((String)map.get("role"));
		
		
		return user;
	}
	
	//Userテーブル全件を取得
	@Override
	public List<User> selectMany() throws DataAccessException {

		//M_USERテーブルのデータを全件取得
		List<Map<String,Object>> getlist = jdbc.queryForList("SELECT * FROM m_user");
		
		//結果返却用リスト
		List<User> userList = new ArrayList<>();
		
		//取得結果を順に格納
		for(Map<String,Object> map : getlist) {
			
			User user = new User();
			
			user.setUserId((String)map.get("user_id"));
			user.setPassword((String)map.get("password"));
			user.setUserName((String)map.get("user_name"));
			user.setBirthday((Date)map.get("birthday"));
			user.setAge((int)map.get("age"));
			user.setMarriage((Boolean)map.get("marriage"));
			user.setRole((String)map.get("role"));
			
			userList.add(user);
			
		}
		return userList;
	}
	
	//1件更新
	@Override
	public int updateOne(User user) throws DataAccessException {
		
		int rowNumber = jdbc.update("UPDATE M_USER "
				+ "SET "
				+ "password = ?,"
				+ "user_name = ?,"
				+ "birthday = ?,"
				+ "age = ?,"
				+ "marriage = ?"
				+ "WHERE user_id = ?"
				, user.getPassword()
				, user.getUserName()
				, user.getBirthday()
				, user.getAge()
				, user.isMarriage()
				, user.getUserId());
		
		return rowNumber;
	}
	
	@Override
	public int deleteOne(String userId) throws DataAccessException {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}
	
	@Override
	public void userCsvOut() throws DataAccessException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
