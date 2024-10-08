package com.example.demo.model;

import java.util.Objects;

import lombok.Data;

@Data
public class TicketSharingId {
	
	private Integer ticketId;
    private Integer sharedWithId;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketSharingId that = (TicketSharingId) o;
        return Objects.equals(ticketId, that.ticketId) &&
               Objects.equals(sharedWithId, that.sharedWithId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, sharedWithId);
    }

}
