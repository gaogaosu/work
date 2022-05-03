package com.wfs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wfs.bean.Gender;
import com.wfs.mapper.GenderMapper;

/**
 * 
 * @author 王方石
 *
 */
@Service
public class GenderServiceImpl implements GenderService{

	@Autowired
	private GenderMapper genderMapper;	
	@Override
	public List<Gender> getGenderList() {
		// 添加雇员信息是，性别选择栏
		return genderMapper.getGenderList();
	}

}
