package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TicketUpdate;

@Repository
public interface TicketUpdateRepository extends JpaRepository<TicketUpdate, Integer>{

}
