package com.xixi.web4j.repository.jdbc;

import static org.junit.Assert.assertNotNull;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

public class UserInfoDaoTest {

	private UserInfoDao userInfoDao;
	public UserInfoDaoTest(){
		ClassPathXmlApplicationContext c=new ClassPathXmlApplicationContext("classpath:spring/business-config.xml");
		userInfoDao=(UserInfoDao)c.getBean("userInfoDao");
	}
	
	@Test
	public void findByIdMap() {
		assertNotNull(userInfoDao);
	}
}
