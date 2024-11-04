package com.example.kouki.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kouki.entity.shiftlist;

public interface ShiftListRepository extends JpaRepository<shiftlist, String> {

}
