package com.pingan.bill.sysmanage.org.util;

import java.io.File;
import java.util.ArrayList;

public class FileUtilMy {

	public static void main(String[] args) {

	}

	/**
	 * 递归扫描目录中所有的后缀名为suffix的文件
	 * @param dirPath 扫描文件的路径
	 * @param fileDataList 输出列表 文件的路径
	 * @param suffix 后缀名
	 */
	public static void scanFile(String dirPath, ArrayList<String> filePathList, Object suffix){
	
		try {
			File scanFile = new File(dirPath);
			File[] files = scanFile.listFiles();
			
			for(int i = 0; i < files.length; i++) {
				if(null != files[i] && files[i].isFile()){ //如果是文件
					if(files[i].getName().endsWith((String)suffix)){
						filePathList.add(files[i].getPath()); //如果想要是文件集合则fileDataList.add(files[i]);
					}
				} else {
					scanFile(files[i].getAbsolutePath(), filePathList, suffix);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 扫描指定目录中所有的文件名, 不递归
	 * @param dirPath
	 * @param fileDataList
	 */
	public static void scanFile(String dirPath, ArrayList<String> fileNameList){
		File scanFile = new File(dirPath);
		File[] files = scanFile.listFiles();
		for(int i = 0; i < files.length; i++){
			if(null != files[i] && files[i].isFile()){
				fileNameList.add(files[i].getName());
			}
		}
	}
}
