package com.app.login.domain.repository.jdbc;

import java.util.List;

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
	
	@Override
	public int count() throws DataAccessException {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}
	
	@Override
	public int insertOne(User user) throws DataAccessException {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}
	
	@Override
	public User selectOne(String userId) throws DataAccessException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
	@Override
	public List<User> selectMany() throws DataAccessException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
	@Override
	public int updateOne(User user) throws DataAccessException {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
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