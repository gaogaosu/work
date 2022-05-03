package com.wfs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wfs.bean.Empdata;
import com.wfs.form.EmpForm;
import com.wfs.mapper.EmpMapper;

/**
 * 
 * @author 王方石
 *
 */
@Service
public class EmpServiceImpl implements EmpService{

	@Autowired
	private EmpMapper empMapper;
	
	//社员情报一览
	@Override
	public List<Empdata> listEmp() {
		return empMapper.listEmp();
	}
	
	//关键词查询到的雇员信息
	@Override
	public List<Empdata> searchEmp(String keyWord) {
		return empMapper.searchEmp(keyWord);
	}
	
	//根据empCd获取雇员详细信息
	@Override
	public Empdata getEmpData(String empCd) {
		return empMapper.getEmpData(empCd);
	}
	
	//添加雇员信息
	@Override
	public void insertEmp(EmpForm empForm) {
		empMapper.insertEmp(empForm);
		
	}

	//更改雇员信息
	@Override
	public void changeEmp(EmpForm empForm) {
		empMapper.changeEmp(empForm);
		
	}

	//删除雇员信息
	@Override
	public void deleteEmp(String empCd) {
		empMapper.deleteEmp(empCd);	
	}

}
