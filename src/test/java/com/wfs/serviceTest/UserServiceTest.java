package com.wfs.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.wfs.service.UserService;
import com.wfs.form.UserForm;
import com.wfs.CompanysystemApplicationTests;

/**
 * 
 * @author 王方石
 *
 */
public class UserServiceTest extends CompanysystemApplicationTests{

	@Autowired
	private UserService userService;
	@Autowired
	private MessageSource messageSource;
	private Locale locale = Locale.getDefault();
	
	//帐号密码正确的情况
	@Test
	public void testGetResult1() throws Exception {
		UserForm loginForm = new UserForm();
		loginForm.setAccountId("111@elife.com");
		loginForm.setPassword("000001");
		List<String> errors = null;
		errors = userService.getResult(loginForm, locale);
		Assertions.assertEquals(0, errors.size());
	}
	//帐号密码错误
		@Test
		public void testGetResult2() throws Exception {
			UserForm loginForm = new UserForm();
			//录入错误的帐号信息
			loginForm.setAccountId("111@aaa.com");
			//且密码错误
			loginForm.setPassword("000001");
			List<String> errors = null;
			errors = userService.getResult(loginForm, locale);
			//预期的结果信息显示"login.message.accountId.error"
			assertEquals(messageSource.getMessage("login.message.accountId.error", null, locale), 
			errors.get(0));
		}
		//帐号正确，密码错误
		@Test
		public void testGetResult3() throws Exception {
			UserForm loginForm = new UserForm();
			//录入正确的帐号信息
			loginForm.setAccountId("111@elife.com");
			//密码错误
			loginForm.setPassword("000002");
			List<String> errors = null;
			errors = userService.getResult(loginForm,  locale);
			//预期的结果信息显示"login.message.password.error"
			assertEquals(messageSource.getMessage("login.message.password.error", null, locale), 
			errors.get(0));
		}
}
