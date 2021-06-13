package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
	@Query("SELECT t FROM Trip t WHERE t.username = :username")
	List<Trip> findAllByUsername(@Param("username") String username);
	
	@Query("DELETE FROM Trip t WHERE t.trip_id = :trip_id")
    @Transactional
    @Modifying
    void deleteByTripId(@Param("trip_id") Integer trip_id);
}
