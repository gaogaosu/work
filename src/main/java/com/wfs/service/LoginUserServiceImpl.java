package com.wfs.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wfs.bean.LoginUser;
import com.wfs.mapper.LoginUserMapper;

/**
 * 
 * @author 王方石
 *
 */
@Service
public class LoginUserServiceImpl implements LoginUserService{

	@Resource//等同于@Autowired
	private LoginUserMapper loginUserMapper;
	
	@Override
	public LoginUser findUser(String accountId) {
		return loginUserMapper.findUser(accountId);
	}

}
