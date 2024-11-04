package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "praent")
public class praent {
	@Id
	@Column(name = "keycol")

	private String keycol;

	@Column(name = "optcol1")

	private String optcol1;

	@Column(name = "optcol2")

	private String optcol2;

	public String getKeycol() {
		return keycol;
	}

	public void setKeycol(String keycol) {
		this.keycol = keycol;
	}

	public String getOptcol1() {
		return optcol1;
	}

	public void setOptcol1(String optcol1) {
		this.optcol1 = optcol1;
	}

	public String getOptcol2() {
		return optcol2;
	}

	public void setOptcol2(String optcol2) {
		this.optcol2 = optcol2;
	}
}
