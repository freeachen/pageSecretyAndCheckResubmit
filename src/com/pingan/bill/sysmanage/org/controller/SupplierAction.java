package com.pingan.bill.sysmanage.org.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pingan.bill.sysmanage.org.entity.Page;
import com.pingan.bill.sysmanage.org.entity.Supplier;
import com.pingan.bill.sysmanage.org.service.SupplierService;


@Controller
@RequestMapping("/supplier")
public class SupplierAction extends BaseAction {
	//@Resource
	@Autowired //也可以
	private SupplierService supplierService;
	
	@RequestMapping(value="/insert")
	@ResponseBody 
	public Object insert(Supplier supplier){
		System.out.println("你好大写.");
		System.out.println("---action.supplier:"+supplier);
		int i = 0;
		try {
			i = supplierService.insert(supplier);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	//根据主键修改供应商的信息
	@RequestMapping(value="/update")
	@ResponseBody
	public Object update(Supplier supplier){
		System.out.println("---action.update.supplier:"+supplier);
		int i = 0;
		try {
			i = supplierService.update(supplier);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	@RequestMapping("/deleteList")
	@ResponseBody
	public Object deleteList(String[] pks){
		System.out.println("---doAjax.deleteList:"+pks);
		int i = 0;
		try {
			i = supplierService.deleteList(pks);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	
	//通过关键字分页查询
	@RequestMapping("/selectPage")
	@ResponseBody 
	public Object selectPage(Page<Supplier> page){
		
		
		Page p = supplierService.selectPage(page);
		System.out.println("----page:"+page);
		//supplier.setSupName("supName1");
		/*Map<String, Object> map =new HashMap<String, Object>();
		map.put("total",p.getTotalRecord());
		map.put("rows",p.getList());*/
		return page.getPageMap();
	}
	
	//通过关键字分页查询
	@RequestMapping("/selectPageUseDyc")
	@ResponseBody 
	public Object selectPageUseDyc(Page<Supplier> page, Supplier supplier){
		
		page.setParamEntity(supplier);
		System.out.println("----page:"+page);

		Page<Supplier> p = supplierService.selectPageUseDyc(page);
		//supplier.setSupName("supName1");
		/*Map<String, Object> map =new HashMap<String, Object>();
		map.put("total",p.getTotalRecord());
		map.put("rows",p.getList());*/
		System.out.println(p.getPageMap());
		return p.getPageMap();
	}
	
	//通过关键字分页查询
/*	@RequestMapping("/selectPage")
	@ResponseBody //如果返回json格式，需要这个注解，这里用来测试环境
	public Object selectPage(Page<Supplier> page,HttpServletRequest request){
		String pageIndex=request.getParameter("page");
		String pageSize =  request.getParameter("rows");
		String keyWord =  request.getParameter("keyWord");
		System.out.println("---doAjax.page||rows||keyWord:"+pageIndex+","+pageSize+","+keyWord);

		Page<Supplier> page = new Page<Supplier>();
		page.setPageIndex(Integer.parseInt(pageIndex));
		page.setPageSize(Integer.parseInt(pageSize));
		page.setKeyWord(keyWord);
		Page p = supplierService.selectPage(page);
		System.out.println("----page:"+page);
		//supplier.setSupName("supName1");
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("total",page.getTotalRecord());
		map.put("rows",page.getList());
		return map;
	}*/
	
	/*@RequestMapping(value="/supplierlist")
	public String list(){
		System.out.println("---action.list:");
	
		return "forward:/WEB-INF/supplier/supplierlist.jsp";
	}*/
	
	@RequestMapping("/doAjax")
	@ResponseBody //如果返回json格式，需要这个注解，这里用来测试环境
	public Object doAjax(Supplier supplier){
		System.out.println("---doAjax.supplier:"+supplier);
		supplier.setSupName("supName1");
		return supplier;
	}


}
