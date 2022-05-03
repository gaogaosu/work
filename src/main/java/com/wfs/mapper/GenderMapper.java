package com.wfs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wfs.bean.Gender;

/**
 * 
 * @author 王方石
 *
 */
@Mapper
public interface GenderMapper {

	public List<Gender> getGenderList();
}
