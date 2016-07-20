package com.spdy.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spdy.bean.Email;
import com.spdy.service.TestService;

/**
 * 测试请求控制器
 * @author djk
 *
 */
@Controller
public class TestController {
	
	@Resource
	private TestService testService;

	/**
	 * 第一个数据源的接口
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Email testQuery(HttpServletRequest request ) {
		System.out.println(request.getLocalAddr());
		System.out.println("--------");
		return testService.quert();
	}
	
	@RequestMapping("/test")
	public String test(){
		System.out.println("hello");
		return "hello";
	}
	
	
	
}
