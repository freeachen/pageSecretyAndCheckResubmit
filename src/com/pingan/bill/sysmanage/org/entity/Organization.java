package com.pingan.bill.sysmanage.org.entity;

public class Organization {
	
	private String org_id;
	private String org_name;
	private String org_type;
	private String org_supperId;
	private String org_area;
	private String org_contact;
	private String org_phone;
	private String org_post;
	private String org_address;
	
	public Organization() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Organization(String org_id, String org_name, String org_type,
			String org_supperId, String org_area, String org_contact,
			String org_phone, String org_post, String org_address) {
		super();
		this.org_id = org_id;
		this.org_name = org_name;
		this.org_type = org_type;
		this.org_supperId = org_supperId;
		this.org_area = org_area;
		this.org_contact = org_contact;
		this.org_phone = org_phone;
		this.org_post = org_post;
		this.org_address = org_address;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getOrg_type() {
		return org_type;
	}

	public void setOrg_type(String org_type) {
		this.org_type = org_type;
	}

	public String getOrg_supperId() {
		return org_supperId;
	}

	public void setOrg_supperId(String org_supperId) {
		this.org_supperId = org_supperId;
	}

	public String getOrg_area() {
		return org_area;
	}

	public void setOrg_area(String org_area) {
		this.org_area = org_area;
	}

	public String getOrg_contact() {
		return org_contact;
	}

	public void setOrg_contact(String org_contact) {
		this.org_contact = org_contact;
	}

	public String getOrg_phone() {
		return org_phone;
	}

	public void setOrg_phone(String org_phone) {
		this.org_phone = org_phone;
	}

	public String getOrg_post() {
		return org_post;
	}

	public void setOrg_post(String org_post) {
		this.org_post = org_post;
	}

	public String getOrg_address() {
		return org_address;
	}

	public void setOrg_address(String org_address) {
		this.org_address = org_address;
	}

	@Override
	public String toString() {
		return "Organization [org_id=" + org_id + ", org_name=" + org_name
				+ ", org_type=" + org_type + ", org_supperId=" + org_supperId
				+ ", org_area=" + org_area + ", org_contact=" + org_contact
				+ ", org_phone=" + org_phone + ", org_post=" + org_post
				+ ", org_address=" + org_address + "]";
	}
	
	
	
	

}
