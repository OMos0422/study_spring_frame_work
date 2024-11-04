package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "moben")
public class moben {
	@Id
	@Column(name = "mobid")

	private String mobid;

	@Column(name = "mobname")

	private String mobname;

	@Column(name = "mobpass")

	private String mobpass;

	public String getMobid() {
		return mobid;
	}

	public void setMobid(String mobid) {
		this.mobid = mobid;
	}

	public String getMobname() {
		return mobname;
	}

	public void setMobname(String mobname) {
		this.mobname = mobname;
	}

	public String getMobpass() {
		return mobpass;
	}

	public void setMobpass(String mobpass) {
		this.mobpass = mobpass;
	}
}
