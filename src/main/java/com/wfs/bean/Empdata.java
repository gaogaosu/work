package com.wfs.bean;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author 王方石
 *
 */
@Getter
@Setter
public class Empdata {

	private String empCd;
	private String name;
	private Date birthday;
	private Nationality nationality;
	private Gender gender;

}
