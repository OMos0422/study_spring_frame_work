package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.sheetreserve;

public interface SheetReserveRepository extends JpaRepository<sheetreserve, String> {

	public List<sheetreserve> findByYoyakubangoLikeAndYoyakubi(String yoyakubango, String yoyakubi);

}
