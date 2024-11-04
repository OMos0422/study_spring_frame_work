package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "zukan")
public class zukan {

	@Id
	@Column(name = "no")

	private int no;
	
	@Column(name = "name")

	private String name;

	@Column(name = "type")

	private String type;

	@Column(name = "seibetsu")

	private String seibetsu;

	@Column(name = "level")

	private String level;

	@Column(name = "ball")

	private String ball;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSeibetsu() {
		return seibetsu;
	}

	public void setSeibetsu(String seibetsu) {
		this.seibetsu = seibetsu;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getBall() {
		return ball;
	}

	public void setBall(String ball) {
		this.ball = ball;
	}
	
	

}