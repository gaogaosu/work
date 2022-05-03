package com.wfs.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wfs.bean.Empdata;
import com.wfs.bean.Gender;
import com.wfs.bean.Nationality;
import com.wfs.form.EmpForm;
import com.wfs.service.EmpService;
import com.wfs.service.GenderService;
import com.wfs.service.NationalityService;

/**
 * 
 * @author 王方石
 * 04/24/2022
 * @version 1.0
 * 描述：雇员信息编辑的功能实现
 */
@Controller
public class ChangeController {

	@Autowired
	private NationalityService nationalityService;
	@Autowired
	private GenderService genderService;
	@Autowired
	private EmpService empService;	
	
	@Autowired
	HttpSession session; 

	// 到添加雇员信息页面
	/*
	 * @ModelAttribute注解运用于有返回值的方法 ：
	 * @RequestMapping的值变成了是要跳转到的jsp界面的名字，
	 * return后面的值赋给了@ModelAttribute后面的值，
	 * @ModelAttribute后面的值变成了 跳转的界面要用EL表达式接收的变量名
	 */
	@GetMapping(value = "/toEmpChange")
	public String toAddEmp(@RequestParam(value = "empCd") String empCd, @ModelAttribute("form") EmpForm empForm) {
		// 根据社员番号获取社员情报
		Empdata empData = empService.getEmpData(empCd);
		//讲获取的情报添加到empForm中，并在页面中获取
		empForm.setEmpCd(empData.getEmpCd());
		empForm.setName(empData.getName());
		empForm.setBirthday(empData.getBirthday().toString());
		empForm.setNationalityCd(empData.getNationality().getNationalityCd());
		empForm.setGenderCd(empData.getGender().getGenderCd());
		// 获取国籍一览
		List<Nationality> nationalityList = nationalityService.getNationalityList();
		// 添加到session内，在页面显示选择用，下性别同上
		session.setAttribute("nationalityList", nationalityList);
		
		List<Gender> genderList = genderService.getGenderList();
		session.setAttribute("genderList", genderList);
		// 到登录新雇员页面
		return ("empChange");
	}
	@PostMapping("/empChange")
	public String changeEmp(@ModelAttribute("form")  @Valid EmpForm empForm, BindingResult result,
	@RequestParam(value="empCd") String empCd, Model model) {		
		if (result.hasErrors()) {
			List<ObjectError> errorList = result.getAllErrors();
			model.addAttribute("errorList", errorList);
			return "empChange";
		}else {		
			empService.changeEmp(empForm);
			return "redirect:/empList";
		}
}
}
