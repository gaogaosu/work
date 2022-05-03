package com.wfs.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.ObjectError;

import com.wfs.controller.LoginUserController;
import com.wfs.CompanysystemApplicationTests;

/**
 * 
 * @author 王方石
 *MockMvc是服务端 Spring MVC测试支持的主入口点。可以用来模拟客户端请求，用于测试。
 *
 */
public class LoginUserControllerTest extends CompanysystemApplicationTests{

	@Autowired//让实例对象正常注入
	@InjectMocks//向里面添加@Mock注入的对象
	private LoginUserController loginUserController;
	@Autowired
	private MessageSource messageSource;
	private Locale locale = Locale.getDefault();
	MockMvc mockMvc;
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(loginUserController).build();
	}
	//帐号密码正确，登录成功
		@Test
		public void testLoginSuccess() throws Exception {
			//用于构建 MockHttpServletRequest，它用于作为 MockMvc的请求对象
			MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.post("/auth")
			.param("accountId","111@elife.com")
			.param("password","000001");
			//mockMvc.perform(getRequest);执行请求，需要传入 MockHttpServletRequest 对象【请求对象】
			ResultActions results = mockMvc.perform(getRequest);
			//测试执行时，生成日志
			results.andDo(print());
			//results.andExpect是执行预期匹配
			//预期页面跳转至success
			results.andExpect(view().name("/success"));
			//预期错误信息数为0
			results.andExpect(model().errorCount(0));
		}
		
		 //帐号为空
		@Test
		public void testAccountIdIsEmpty() throws Exception {
			//用mock建立post访问对象/auth
			MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.post("/auth")
			.param("accountId", "")
			.param("password", "000001");
			//用mock执行/auth
			ResultActions results = mockMvc.perform(getRequest);
			//测试执行时，生成日志
			results.andDo(print());
			//预期结果
			results.andExpect(view().name("/login"));
			results.andExpect(model().errorCount(1));//获取一条错误信息，帐号为空
			//获取错误信息表
			//告诉编译器忽略 unchecked 警告信息，如使用List，ArrayList等未进行参数化产生的警告信息
			@SuppressWarnings("unchecked")
			List<ObjectError> errorList = (List<ObjectError>) results.andReturn().getModelAndView().getModel()
			.get("errorList");
			//获取错误信息
			String message = errorList.get(0).getDefaultMessage();
			//预期错误信息为login.error.accountId.notEmpty
			assertTrue(message.contains("login.error.accountId.notEmpty"));
		}
		
		//帐号格式错误
		@Test
		public void testAccountIdNotMail() throws Exception {
			//用mock建立post访问对象/auth
			MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.post("/auth")
					.param("accountId", "111")
					.param("password", "000001");
			//用mock执行/auth
			ResultActions results = mockMvc.perform(getRequest);
			//测试执行时，生成日志
			results.andDo(print());
	        //预期结果，访问/login，错误信息为1条
			results.andExpect(view().name("/login"));
			results.andExpect(model().errorCount(1));
			//获取错误信息表 
			//告诉编译器忽略 unchecked 警告信息，如使用List，ArrayList等未进行参数化产生的警告信息
			@SuppressWarnings("unchecked")
			List<ObjectError> errorList = (List<ObjectError>) results.andReturn().getModelAndView().getModel()
					.get("errorList");
			//获取错误信息
			String message = errorList.get(0).getDefaultMessage();
			//预期错误信息为login.error.accountId.isEmail
			assertTrue(message.contains("login.error.accountId.isEmail"));
		}
		
		//密码未输入
		@Test
		public void testPasswordIsEmpty() throws Exception {
			//用mock建立post访问对象/auth,模拟页面输入项
			MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.post("/auth")
					.param("accountId", "111@elife.com")
					.param("password", "");
			//用mock执行/auth
			ResultActions results = mockMvc.perform(getRequest);
			//测试执行时，生成日志
			results.andDo(print()); 
			//预期结果，访问/login，错误信息为2条，密码为空和密码不为6位数
			results.andExpect(view().name("/login"));
			results.andExpect(model().errorCount(2));
			//获取错误信息表 
			//告诉编译器忽略 unchecked 警告信息，如使用List，ArrayList等未进行参数化产生的警告信息
			@SuppressWarnings("unchecked")
			List<ObjectError> errorList = (List<ObjectError>) results.andReturn().getModelAndView().getModel()
					.get("errorList");		
			List<String> messages = new ArrayList<>();
			//获取错误信息
			for(ObjectError error:errorList) {
				String message = error.getDefaultMessage();
				messages.add(message);
			}
			//预期错误信息中包含"{login.error.password.notEmpty}"
			assertTrue(messages.contains("{login.error.password.notEmpty}"));
		}

		//密码不为6位数
		@Test
		public void testPasswordLength() throws Exception {
			//用mock建立post访问对象/auth
			MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.post("/auth")
					.param("accountId", "111@elife.com")
					.param("password", "0000000");
			//用mock执行/auth
			ResultActions results = mockMvc.perform(getRequest);
			//测试执行时，生成日志
			results.andDo(print());
			//预期结果，访问/login，错误信息为1条
			results.andExpect(view().name("/login"));
			results.andExpect(model().errorCount(1));
			//获取错误信息表 
			//告诉编译器忽略 unchecked 警告信息，如使用List，ArrayList等未进行参数化产生的警告信息
			@SuppressWarnings("unchecked")
			List<ObjectError> errorList = (List<ObjectError>) results.andReturn().getModelAndView().getModel()
					.get("errorList");
			String message = errorList.get(0).getDefaultMessage();
			//预期错误信息为"login.error.password.length"
			assertTrue(message.contains("login.error.password.length"));
		}
		//帐号不存在
		@Test
		public void testLoginAccountError() throws Exception {
			//用mock建立post访问对象/auth
			MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.post("/auth")
					.param("accountId", "111@aaa.com")
					.param("password", "000001");
			//本地化
			getRequest.locale(locale);
			//用mock执行/auth行
			ResultActions results = mockMvc.perform(getRequest);
			//测试执行时，生成日志
			results.andDo(print());
		    //预期结果，访问/login，错误信息为0条
			results.andExpect(view().name("/login"));
			results.andExpect(model().errorCount(0));
			//取的逻辑错误信息
			String message = (String) results.andReturn().getModelAndView().getModel().get("message");
			//预期错误信息为"login.message.accountId.error"
			assertEquals(messageSource.getMessage("login.message.accountId.error", null, locale), message);
		}
		//密码错误
		@Test
		public void testLoginPasswordError() throws Exception {
			//用mock建立post访问对象/auth
			MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.post("/auth")
					.param("accountId", "111@elife.com")
					.param("password", "000002");
			//本地化
			getRequest.locale(locale);
			//用mock执行/auth行
			ResultActions results = mockMvc.perform(getRequest);
			//测试执行时，生成日志
			results.andDo(print());
		    //预期结果，访问/login，错误信息为0条
			results.andExpect(view().name("/login"));
			results.andExpect(model().errorCount(0));
			//取的逻辑错误信息
			String message = (String) results.andReturn().getModelAndView().getModel().get("message");
			//预期错误信息为"login.message.password.error"
			assertEquals(messageSource.getMessage("login.message.password.error", null, locale), message);
		}
		
}
