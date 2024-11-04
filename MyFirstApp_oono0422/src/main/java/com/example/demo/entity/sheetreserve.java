package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sheetreserve")
public class sheetreserve {
	@Id
	@Column(name = "yoyakubango")

	private String yoyakubango;

	@Column(name = "yoyakubi")

	private String yoyakubi;

	@Column(name = "yoyakuname")

	private String yoyakuname;

	public String getYoyakubango() {
		return yoyakubango;
	}

	public void setYoyakubango(String yoyakubango) {
		this.yoyakubango = yoyakubango;
	}

	public String getYoyakubi() {
		return yoyakubi;
	}

	public void setYoyakubi(String yoyakubi) {
		this.yoyakubi = yoyakubi;
	}

	public String getYoyakuname() {
		return yoyakuname;
	}

	public void setYoyakuname(String yoyakuname) {
		this.yoyakuname = yoyakuname;
	}

}
