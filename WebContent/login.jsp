<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.pingan.bill.sysmanage.org.util.RSAUtils"%>
<%@ page import="java.security.interfaces.RSAPublicKey"%>
<%@ page import="java.security.interfaces.RSAPrivateKey"%>
    <%  
        HashMap<String, Object> map = RSAUtils.getKeys();    
        //生成公钥和私钥    
        RSAPublicKey publicKey = (RSAPublicKey) map.get("public");    
        RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");  
          
        session.setAttribute("privateKey", privateKey);//私钥保存在session中，用于解密  
          
        //公钥信息保存在页面，用于加密  
        String publicKeyExponent = publicKey.getPublicExponent().toString(16);  
        String publicKeyModulus = publicKey.getModulus().toString(16);  
        request.setAttribute("publicKeyExponent", publicKeyExponent);  
        request.setAttribute("publicKeyModulus", publicKeyModulus);  
    %>  

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.5/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/security.js"></script>
	
	<script type="text/javascript">
		function login(){
			var name = $("input[name='loginName']").val();
			var pwd = $("input[name='password']").val();
			//给密码加密
		    RSAUtils.setMaxDigits(200);  
		    //setMaxDigits(256);  
		    var key = new RSAUtils.getKeyPair("${publicKeyExponent}", "", "${publicKeyModulus}");  
		    var encrypedPwd = RSAUtils.encryptedString(key,pwd); 
		    if(name.length > 0 && pwd.length > 0){
		   	 	$("input[name='password']").val(encrypedPwd);//加密后的密码传到后台去解密,同时加密后赋值给密码框别人就看不到了   
		    	document.forms['login_form'].submit();
		    }else {
		    	$("#errormsg").val("请输入用户名或密码");
		    }
		}
		
		$(document).keypress(function(e){
			if(e.keyCode == 13 || e.keyCode == 8){
				login();
			}
		});
		
	</script>
</head>
<body>
<form id="login_form" action="${pageContext.request.contextPath }/portal/login.action" method="post">
	<table>
		<tr><td colspan="2"><label id="errormsg" style="color:red">${errormsg }</label></td></tr>
		<tr> 
			<td>登录账号:</td>
			<td><input id="loginName" name="loginName" type="text"></td>
		</tr>
		<tr> 
			<td>密码:</td>
			<td><input id="password" name="password" type="text"></td>
			<td><input name="token" type="hidden" value="${token }"></td>
		</tr>
		<tr> 
			<td><input value="登录" type="button"  onclick="login();"></td>
		</tr>
	</table>
</form>
</body>
</html>
