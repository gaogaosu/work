package com.wfs.mapperTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wfs.CompanysystemApplicationTests;
import com.wfs.bean.Empdata;
import com.wfs.bean.LoginUser;
import com.wfs.mapper.EmpMapper;
import com.wfs.mapper.LoginUserMapper;

/**
 * 
 * @author 王方石
 *
 */
public class LoginUserMapperTest extends CompanysystemApplicationTests{

	@Autowired
	private LoginUserMapper loginUserMapper;
	@Autowired
	private EmpMapper empMapper;
	
	//帐号存在的情况下
	@Test
	public void testQueryUser1() {
		LoginUser lu=loginUserMapper.findUser("111@elife.com");
		assertEquals("111@elife.com", lu.getAccountId());
		assertEquals("000001", lu.getPassword());
	}
	//帐号不存在的情况下
	@Test
	public void testQueryUser2() {
		LoginUser lu=loginUserMapper.findUser("1@elife.com");
		assertEquals(null, lu);
	}
	@Test
	public void test2() {
		List<Empdata> emp=empMapper.listEmp();
		for(Empdata emps:emp) {
			System.out.println(emps);
		}
	}
}
