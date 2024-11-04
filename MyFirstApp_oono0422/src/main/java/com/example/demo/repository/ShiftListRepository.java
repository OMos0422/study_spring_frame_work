package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.shiftlist;

public interface ShiftListRepository extends JpaRepository<shiftlist, String> {

	List<shiftlist> findByEmpname(String j);

	List<shiftlist> findByWorkdate(String j);

	List<shiftlist> findByStarttime(String j);

	List<shiftlist> findByEndtime(String j);

	List<shiftlist> findByJikyu(String j);

}
