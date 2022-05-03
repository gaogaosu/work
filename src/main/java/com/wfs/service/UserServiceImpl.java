package com.wfs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.wfs.bean.LoginUser;
import com.wfs.form.UserForm;
import com.wfs.mapper.LoginUserMapper;

/**
 * 
 * @author 王方石
 *
 */
@Service
public class UserServiceImpl implements UserService{

	@Resource
	private LoginUserMapper loginUserMapper;
	@Autowired
	private MessageSource messageSource;

	@Override//Locale locale当前语言环境
	public List<String> getResult(UserForm userForm, Locale locale) {
		LoginUser lu=loginUserMapper.findUser(userForm.getAccountId());
		List<String> errorlist=new ArrayList<String>();
		if(lu==null) {
			//根据key找到国际化相关的信息，第二个参数没有数组，所以为null，第三个参数，本地化语言环境对象
			errorlist.add(messageSource.getMessage("login.message.accountId.error", null,locale));
		}else if(!lu.getPassword().equals(userForm.getPassword())) {
			//添加错误信息和国际化相关的对象，将信息添加到集合对象中
			errorlist.add(messageSource.getMessage("login.message.password.error", null,locale));
		}
		return errorlist;
	}

}
