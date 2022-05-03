package com.wfs.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
 * 描述：雇员添加画面的功能实现
 */
@Controller
public class AddEmpController {
	
	/*
	 * NationalityService类型的属性 nationalityService
	 *@Autowired自动注入
	 */
	@Autowired
	private NationalityService nationalityService;
	@Autowired
	private GenderService genderService;
	@Autowired
	private EmpService empService;	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	HttpSession session; 

	// 到添加雇员信息页面
	/*
	 * @ModelAttribute注解运用于有返回值的方法 ：
	 * @RequestMapping的值变成了是要跳转到的jsp界面的名字，
	 * return后面的值赋给了@ModelAttribute后面的值，
	 * @ModelAttribute后面的值变成了 跳转的界面要用EL表达式接收的变量名
	 */
	/*
	 * 方法名：toAddEmp
	 * 范围修饰符：public
	 * 返回值：String 需要访问的页面
	 * 参数：
	 * @ModelAttribute("form") EmpForm form, 
	 * Model model
	 * 功能：页面信息获取
	 */
	@GetMapping(value = "/toAddEmp")
	public String toAddEmp(@ModelAttribute("form") EmpForm form, Model model) {
		// 获取国籍一览
		List<Nationality> nationalityList = nationalityService.getNationalityList();
		// 添加到session内，在页面显示选择用，下姓别同上
		session.setAttribute("nationalityList", nationalityList);
		
		List<Gender> genderList = genderService.getGenderList();
		session.setAttribute("genderList", genderList);
		// 到登录新雇员页面
		return ("addEmp");
	}

	// 登录新的雇员信息
	//@Valid 注解通常用于对象属性字段的规则检测
	
	 /* 方法名：AddEmp
	 * 范围修饰符：public
	 * 返回值：String 需要访问的页面
	 * 参数：
	 * @ModelAttribute("form") @Valid EmpForm empForm
	 * BindingResult result
	 * Model model 
	 * Locale locale
	 *功能：雇员信息添加
	 */
	@PostMapping( "/addEmp")
	public String addEmp(@ModelAttribute("form") @Valid EmpForm empForm, BindingResult result, 
				Model model, Locale locale) {		
		if (result.hasErrors()) {
			//验证信息是否全部填写正确
			List<ObjectError> errorList = result.getAllErrors();
			// 将错误信息添加到model内
			model.addAttribute("errorList", errorList);
			// 重新填写
			return ("addEmp");
		} else {
            //做验证
			Empdata empData = empService.getEmpData(empForm.getEmpCd());
			//雇员编号已存在
			if (empData != null) {
				//将错误信息添加到model内
				model.addAttribute("message", messageSource.getMessage("addEmp.error", null, locale));
				// 返回
				return ("addEmp");
			} else {
				// 登录雇员信息
				empService.insertEmp(empForm);
				// 返回社员一览
				return ("redirect:/empList");
			}
		}
	}
}
