package com.wfs.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.wfs.bean.LoginUser;

/**
 * 
 * @author 王方石
 *
 */
@Mapper
public interface LoginUserMapper {

	public LoginUser findUser(String accountId);
}
