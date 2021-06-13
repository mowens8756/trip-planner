package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Itinerary;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Integer> {
	
    @Query("DELETE FROM Itinerary i WHERE i.trip_id = :trip_id")
    @Transactional
    @Modifying
    void deleteByTripId(@Param("trip_id") Integer trip_id);
    
    @Query("SELECT i FROM Itinerary i WHERE i.trip_id = :trip_id")
	List<Itinerary> findByTripId(@Param("trip_id") Integer trip_id);
    
}
