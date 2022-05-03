package com.wfs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wfs.bean.Nationality;
import com.wfs.mapper.NationalityMapper;

/**
 * 
 * @author 王方石
 *
 */
@Service
public class NationalityServiceImpl implements NationalityService{

	//NationalityMapper を注入する
	@Autowired
	private NationalityMapper  nationalityMapper;
	
	@Override
	public List<Nationality> getNationalityList(){
		//添加雇员时，国籍选项用
		return nationalityMapper.getNationalityList();
	}

}
