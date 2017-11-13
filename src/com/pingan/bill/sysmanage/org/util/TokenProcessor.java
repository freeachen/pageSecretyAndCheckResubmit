package com.pingan.bill.sysmanage.org.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import sun.misc.BASE64Encoder;

public class TokenProcessor {
	   private TokenProcessor(){}
	     
	    private static final TokenProcessor instance = new TokenProcessor();
	     
	    public static TokenProcessor getInstance(){
	        return instance;
	    }
	     
	    public String generateToken(){ //指纹摘要算法
	        //121212  1231646466 4654646464646646 长度可能不一致,但我们需要长度一致
	        String token = System.currentTimeMillis() + new Random().nextInt() + "";
	         
	        MessageDigest md;
	        try {
	            md = MessageDigest.getInstance("md5");
	            byte[] md5 = md.digest(token.getBytes()); //返回的是128位的指纹
	            //String pass = new String(md5); 乱码, 用base64算法返回的是明文字符串即键盘能看到的
	            //base64 把任意3个字节24个二进制位变成4个字节(6位/字节),但一个字节默认是8位,前面补00,
	            //网络上的数据传递一般都是通过base64转过去传递
	            BASE64Encoder encoder = new BASE64Encoder();
	            return encoder.encode(md5);
	             
	        } catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }
	    }
}
