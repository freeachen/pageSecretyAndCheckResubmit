package com.pingan.bill.sysmanage.org.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.pingan.bill.sysmanage.org.dao.AccountMapper;
import com.pingan.bill.sysmanage.org.dao.BaseMapper;
import com.pingan.bill.sysmanage.org.dao.OrganizationMapper;
import com.pingan.bill.sysmanage.org.dao.SupplierMapper;
import com.pingan.bill.sysmanage.org.entity.Page;
import com.pingan.bill.sysmanage.org.service.BaseService;



public class BaseServiceImpl<T> implements BaseService<T> {
	protected  BaseMapper<T> baseMapper;
	
	
	@Autowired
	protected  SupplierMapper supplierMapper;
	
	@Autowired
	protected  AccountMapper accountMapper;
	
	@Autowired
	protected  OrganizationMapper organizationMapper;
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	
	@SuppressWarnings("unused")
	@PostConstruct//在构造方法后，初化前执行, 作用就是让baseMapper 指向不同的实现类如 supplierMapper, accountMapper等
	private void initBaseMapper() throws Exception{
//完成以下逻辑，需要对研发本身进行命名与使用规范
//this: cn.itcast.scm.service.impl.SupplierServiceImpl@208e2fb5
//this.getClass().getSuperclass(): class cn.itcast.scm.service.impl.BaseServiceImpl
//this.getClass().getGenericSuperclass()): cn.itcast.scm.service.impl.BaseServiceImpl<cn.itcast.scm.entity.Supplier>
//field: protected cn.itcast.scm.dao.SupplierMapper cn.itcast.scm.service.impl.BaseServiceImpl.supplierMapper				
//field对应的对象: org.apache.ibatis.binding.MapperProxy@56de24c5
//baseField:protected cn.itcast.scm.dao.BaseMapper cn.itcast.scm.service.impl.BaseServiceImpl.baseMapper
//baseField对应的对象:null
//baseField对应的对象:org.apache.ibatis.binding.MapperProxy@56de24c5
//field.get(this)获取当前this的field字段的值。例如：Supplier对象中，baseMapper所指向的对象为其子类型SupplierMapper对象，子类型对象已被spring实例化于容器中		
		
		
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		Class clazz = (Class)type.getActualTypeArguments()[0]; //class cn.itcast.scm.entity.Supplier
		String localField = clazz.getSimpleName().substring(0,1).toLowerCase()+clazz.getSimpleName().substring(1)+"Mapper"; //supplierMapper
		Field field = this.getClass().getSuperclass().getDeclaredField(localField); //SupplierMapper supplierMapper
		Field baseField = this.getClass().getSuperclass().getDeclaredField("baseMapper"); //BaseMapper baseMapper
		baseField.set(this, field.get(this));		//让baseMapper指向不同的子类
	}	
	

	public int insert(T entity) throws Exception {
		System.out.println("====>" + entity);
		return baseMapper.insert(entity);
	}

	public int update(T entity) throws Exception {
		return baseMapper.update(entity);
	}

	public int delete(T entity) throws Exception {
		return baseMapper.delete(entity);
	}

	public int deleteList(String[] pks) throws Exception {
		return baseMapper.deleteList(pks);
	}

	public T select(T entity) {
		return baseMapper.select(entity);
	}

	public Page<T> selectPage(Page<T> page) {
		page.setList(baseMapper.selectPageList(page));
		page.setTotalRecord(baseMapper.selectPageCount(page));
		return page;
	} 

	public Page<T> selectPageUseDyc(Page<T> page) {
		page.setList(baseMapper.selectPageListUseDyc(page));
		page.setTotalRecord(baseMapper.selectPageCountUseDyc(page));
		return page;
	}

}
