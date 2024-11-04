package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.zukan;

public interface ZukanRepository extends JpaRepository <zukan,Integer>{

	List<zukan> findByNo(int no);
	
}