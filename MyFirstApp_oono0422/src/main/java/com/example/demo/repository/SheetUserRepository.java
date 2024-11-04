package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.sheetuser;

public interface SheetUserRepository extends JpaRepository<sheetuser, String> {

}
