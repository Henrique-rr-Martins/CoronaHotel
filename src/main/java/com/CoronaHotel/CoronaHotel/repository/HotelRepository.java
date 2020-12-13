package com.CoronaHotel.CoronaHotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CoronaHotel.CoronaHotel.model.Hotel;	

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer>{}
