package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Itinerary;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Integer> {

}
