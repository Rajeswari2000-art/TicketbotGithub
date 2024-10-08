package com.example.demo.model;

import java.io.Serializable;
import java.util.Objects;

import lombok.Data;

@Data
public class TicketCategoryId implements Serializable{
	
	private Integer ticketId;
    private Integer categoryId;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketCategoryId that = (TicketCategoryId) o;
        return Objects.equals(ticketId, that.ticketId) &&
               Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, categoryId);
    }

}
