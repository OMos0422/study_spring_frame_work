package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.shiftlistcustom;

public interface ShiftListCustomRepository extends JpaRepository<shiftlistcustom, String> {

}
