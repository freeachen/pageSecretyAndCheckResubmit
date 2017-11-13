package com.pingan.bill.sysmanage.org.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pingan.bill.sysmanage.org.entity.Organization;
import com.pingan.bill.sysmanage.org.entity.Page;
import com.pingan.bill.sysmanage.org.service.OrganizationService;

@Controller
@RequestMapping("/organization")
public class OrganizationAction extends BaseAction {
	//@Resource
	@Autowired //也可以
	private OrganizationService organizationService;
	
	@RequestMapping(value="/insert")
	@ResponseBody 
	public Object insert(Organization organization){
		System.out.println("你好大写.");
		System.out.println("---action.organization:"+organization);
		int i = 0;
		try {
			i = organizationService.insert(organization);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	//根据主键修改供应商的信息
	@RequestMapping(value="/update")
	@ResponseBody
	public Object update(Organization organization){
		System.out.println("---action.update.organization:"+organization);
		int i = 0;
		try {
			i = organizationService.update(organization);
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
			i = organizationService.deleteList(pks);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	
	//通过关键字分页查询
	@RequestMapping("/selectPage")
	@ResponseBody 
	public Object selectPage(Page<Organization> page){
		Page p = organizationService.selectPage(page);
		return page.getPageMap();
	}
	
	//通过关键字分页查询
	@RequestMapping("/selectPageUseDyc")
	@ResponseBody 
	public Object selectPageUseDyc(Page<Organization> page, Organization organization){
		page.setParamEntity(organization);
		System.out.println("----page:"+page);
		Page p = organizationService.selectPageUseDyc(page);
		System.out.println(p.getPageMap());
		return p.getPageMap();
	}
	
	
	@RequestMapping("/doAjax")
	@ResponseBody //如果返回json格式，需要这个注解，这里用来测试环境
	public Object doAjax(Organization organization){
		System.out.println("---doAjax.organization:"+organization);
		organization.setOrg_name("zonghang");
		return organization;
	}


}
