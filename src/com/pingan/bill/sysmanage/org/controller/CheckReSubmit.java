package com.pingan.bill.sysmanage.org.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pingan.bill.sysmanage.org.util.TokenProcessor;

@Controller
@RequestMapping("/checkReSubmit")
public class CheckReSubmit {

	@RequestMapping("/backToken")
	public void backToken(HttpServletRequest request, HttpServletResponse response){
	    //产生随机数 表单号
	    TokenProcessor processor = TokenProcessor.getInstance();
	    String token = processor.generateToken();
	    request.getSession().setAttribute("token", token);//带给前台form并为以后校验用,所以要存session
	     
	    try {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
