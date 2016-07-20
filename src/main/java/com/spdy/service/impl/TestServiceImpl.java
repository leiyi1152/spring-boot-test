package com.spdy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spdy.bean.Email;
import com.spdy.mapper.TestMapper;
import com.spdy.service.TestService;

/**
 * 测试服务接口实现类
 * 
 * @author djk
 *
 */
@Service
public class TestServiceImpl implements TestService {

	/**
	 * 第一个数据源的接口
	 */
	@Autowired
	private TestMapper testMapper;

	

	/**
	 * 第一个数据源的测试接口
	 */
	public Email quert() {
		return testMapper.findemail();
	}

	

}
