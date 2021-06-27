package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Access;

public interface AccessRepository extends JpaRepository<Access, Integer> {
	
	@Query("SELECT a FROM Access a WHERE a.trip_id = :trip_id")
	List<Access> findByTripId(@Param("trip_id") Integer trip_id);
	
}
