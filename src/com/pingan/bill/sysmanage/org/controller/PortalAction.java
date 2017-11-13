package com.pingan.bill.sysmanage.org.controller;

import java.security.interfaces.RSAPrivateKey;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pingan.bill.sysmanage.org.entity.Person;
import com.pingan.bill.sysmanage.org.util.RSAUtils;

@Controller
@RequestMapping("/portal")
public class PortalAction {
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(String loginName, String password, String token, HttpServletRequest request, HttpServletResponse response){
	    String errormsg = "";
	    String pwd = ""; // 解密后的密码
	    String loginJsp="forward:/login.jsp";
	    String indexJsp="forward:/index.jsp";
	    
	    //1. 检查是否合法提交表单
	    if(! isTokenValid(request, token)){
	    	errormsg = "Invalid Submit!";
	    	request.setAttribute("errormsg", errormsg);
	    	return loginJsp;
	    }
	    
	    HttpSession session = request.getSession();
	    //2. 解密密码,注意密码是倒置的比如前台输入的是admin123 ,解密后是321
	    try {
	    	RSAPrivateKey privateKey = (RSAPrivateKey)request.getSession().getAttribute("privateKey");  
			String descrypedPwd = RSAUtils.decryptByPrivateKey(password, privateKey);
			StringBuilder sb = new StringBuilder(descrypedPwd);
			pwd = sb.reverse().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	    
	    //3. 干掉session 防止会话标识未更新
	    session.removeAttribute("token");
	    session.setMaxInactiveInterval(0);
	    session.invalidate();
	    Cookie[] cookies = request.getCookies();
	    if(null != cookies) {
	    	for(Cookie cookie : cookies) {
		    	if("JSESSIONID".equalsIgnoreCase(cookie.getName())){
		    		cookie.setMaxAge(0);
		    		response.addCookie(cookie);
		    	}
		    }
	    }
		return indexJsp;
	    
	    //4. 通过账号密码去db中验证用户是否由,有则加入到session中,反之跳到登录页
	/*    Person person = personService.getPersonInfo(loginName);
	    if(null == person || !person.getPassword().equals(MD5Utils.getMD5(pwd))){
	    	errormsg = "账号或密码错误";
	    } else {
	    	if(registerPerson(person, request)){
	    		return indexJsp;
	    	} else {
	    		errormsg = "无权限";
	    	}
	    }
	    request.setAttribute("errormsg", errormsg);
	    return loginJsp;*/
	}

/*	private boolean registerPerson(Person person, HttpServletRequest request){
		Role role = roleService.getRoleByPersonId(person.getPid());
		if(null == role) return false;
		person.setRole(role);
		person.setS_ip("1");
		personService.updateLoginState(person);
		request.getSession().setAttribute("login", person);
		Static.UserMap.put(request.getSession().getId(), person.getPid());
		return true;
	}*/
	
	
	//检验是否合法提交表单
	private boolean isTokenValid(HttpServletRequest request, String clientToken) {
		if(StringUtils.isBlank(clientToken)){
			return false;
		}
		
		String serverToken = (String) request.getSession().getAttribute("token");
		if(StringUtils.isBlank(serverToken)){
			return false;
		}
		
		if( ! clientToken.equals(serverToken)) {
			return false;
		}
		
		return true;
	}
	
	
}
