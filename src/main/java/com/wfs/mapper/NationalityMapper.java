package com.wfs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wfs.bean.Nationality;

/**
 * 
 * @author 王方石
 *
 */
@Mapper
public interface NationalityMapper {

	public List<Nationality> getNationalityList();
}
