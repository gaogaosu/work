package com.wfs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wfs.bean.Empdata;
import com.wfs.service.EmpService;

/**
 * 
 * @author 王方石
 *
 */
@Controller
public class EmpListController {

	@Autowired
	private EmpService empService;
	
	@GetMapping("/empList")
	public String listEmp(Model model) {
		//检索全社员信息
		List<Empdata> empList=empService.listEmp();
		//将list追加进model内
		model.addAttribute("empList", empList);
		return "empList";
	}
	
	//关键词搜索雇员信息
	@PostMapping("/searchEmp")	
	//从页面获得关键词
	public String searchEmp(@RequestParam(value="keyWord") String keyWord,Model model) {
		//通过关键词搜索方法用List<Empdata> empList接受
		List<Empdata> empList = empService.searchEmp(keyWord);
		//将信息添加到model中
		model.addAttribute("empList",empList);	
		return "empList";
	}
	
	//雇员详细信息
	@GetMapping("/showDetails")
	public String showDetails(@RequestParam(value="empCd")String empCd,Model model) {
		//通过雇员编号获取雇员详细信息
		Empdata empData = empService.getEmpData(empCd);
		//将信息添加到model中
		model.addAttribute("empData",empData);
		return "empDetails";
	}
	
	@RequestMapping("/deleteEmp")
	public String deleteEmp(@RequestParam(value="empCd")String empCd) {		
		empService.deleteEmp(empCd);				
		return "redirect:/empList";
	}
}
