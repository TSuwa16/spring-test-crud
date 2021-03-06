package com.app.login.domain.model;

import java.util.Date;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SignupForm {

	@NotBlank
	@Email
	private String userId; //ユーザID
	
	@NotBlank
	@Length(min=4 , max=100)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	private String password; //パスワード
	
	@NotBlank
	private String userName; //ユーザ名
	
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date birthday; //誕生日

	@Min(20)
	@Max(100)
	private int age; //年齢
	
	@AssertFalse
	private boolean marriage; //結婚ステータス
}
