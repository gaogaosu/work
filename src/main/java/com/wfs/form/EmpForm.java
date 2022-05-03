package com.wfs.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author 王方石
 *
 */
@Getter
@Setter
public class EmpForm {

	@NotEmpty(message = "{empCode.notEmpty}")
	private String empCd;
	//^[一-龥 ア-ン あ-ん a-z A-Z]*$  ---输入内容的格式
	@NotEmpty(message = "{empName.notEmpty}")
	@Pattern(regexp = "^[一-龥 ア-ン あ-ん a-z A-Z]*$", message = "{empName.error}")
	private String name;

	@NotEmpty(message = "{empBirthday.notEmpty}")
	private String birthday;
	
	@NotEmpty(message = "{nationality.notEmpty}")
	private String nationalityCd;
	
	@NotEmpty(message = "{empGender.notEmpty}")
	private String genderCd;

}
