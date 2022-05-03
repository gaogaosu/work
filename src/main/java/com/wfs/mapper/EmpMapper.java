package com.wfs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wfs.bean.Empdata;
import com.wfs.form.EmpForm;

/**
 * 
 * @author 王方石
 *
 */
@Mapper
public interface EmpMapper {

	//社员一栏
	public List<Empdata> listEmp();
	
	//关键词查询社员信息
	public List<Empdata> searchEmp(String keyWord);
	
	//根据社员番号查询信息
	public Empdata getEmpData(String empCd);
	
	//添加雇员
	public void insertEmp(EmpForm empForm);
	
	//更改雇员信息
	public void changeEmp(EmpForm empForm);
	
	//删除雇员信息
	public void deleteEmp(String empCd);
	
}
