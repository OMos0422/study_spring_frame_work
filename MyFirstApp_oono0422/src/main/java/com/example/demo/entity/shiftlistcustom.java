package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "shiftlistcustom")
public class shiftlistcustom {

	@Id
	@Column(name = "customjouken")
	private String customjouken;

	public String getCustomjouken() {
		return customjouken;
	}

	public void setCustomjouken(String customjouken) {
		this.customjouken = customjouken;
	}

}
