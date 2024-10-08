package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TicketSharing;
import com.example.demo.model.TicketSharingId;

@Repository
public interface TicketSharingRepository extends JpaRepository<TicketSharing, TicketSharingId>{

}