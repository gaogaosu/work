package com.wfs.controller;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.wfs.form.UserForm;
import com.wfs.service.UserService;

/**
 * 
 * @author 王方石
 *  04/24/2022
 * @version 1.0
 * 描述：登录功能的实现
 */
@Controller
public class LoginUserController {

	/*
	 * UserService类型的属性 userService
	 * @Resource自动注入
	 */
	@Resource//等同于@Autowired
	private UserService userService;
	//登录页面
	/*
	 * 方法名：login
	 * 范围修饰符：public
	 * 返回值：String 需要访问的页面
	 * 参数：
	 * @ModelAttribute("form") EmpForm form, 
	 * Model model
	 * 功能：页面跳转
	 */
	@GetMapping("/login")
	public String login(@ModelAttribute("form")UserForm form,Model model) {
		return ("/login");
	}
	//帐号密码验证
	/*
	 * 方法名：auth
	 * 范围修饰符：public
	 * 返回值：String 需要访问的页面
	 * 参数：
		@ModelAttribute("form")@Valid UserForm userForm
		BindingResult result
		Model model
		Locale locale
	 * 功能：登录验证
	 */
	@PostMapping("/auth")
	public String auth(@ModelAttribute("form")@Valid UserForm userForm,
			BindingResult result,Model model,Locale locale) {
		String url=null;
		if(result.hasErrors()) {
			List<ObjectError> errorList=result.getAllErrors();
			//获取错误信息并添加
			model.addAttribute("errorList",errorList);
			//页面跳转至login
			url="/login";
			return url;
		}
		List<String> errorlist=userService.getResult(userForm, locale);
		if(!(errorlist.size()==0)) {
			model.addAttribute("message", errorlist.get(0));
			//页面跳转至login
			url="/login";
			return url;
		}else {
			url="redirect:/empList";
			return url;
		}
	}
}
