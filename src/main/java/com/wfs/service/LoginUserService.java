package com.wfs.service;

import com.wfs.bean.LoginUser;

/**
 * 
 * @author 王方石
 *
 */
public interface LoginUserService {
	public LoginUser findUser(String accountId);
}
