package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "shiftlist")
public class shiftlist {

	@Id
	@Column(name = "empname")
	private String empname;

	@Column(name = "workdate")
	private String workdate;

	@Column(name = "starttime")
	private String starttime;

	@Column(name = "endtime")
	private String endtime;

	@Column(name = "jikyu")
	private String jikyu;

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getWorkdate() {
		return workdate;
	}

	public void setWorkdate(String workdate) {
		this.workdate = workdate;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getJikyu() {
		return jikyu;
	}

	public void setJikyu(String jikyu) {
		this.jikyu = jikyu;
	}
}
