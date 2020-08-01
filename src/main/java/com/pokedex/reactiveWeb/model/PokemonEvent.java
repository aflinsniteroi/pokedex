/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokedex.reactiveWeb.model;

import java.util.Objects;

/**
 *
 * @author CP39
 */
public class PokemonEvent {
    private Long eventId;
    private String eventType;

    public PokemonEvent(Long eventId, String eventType) {
        this.eventId = eventId;
        this.eventType = eventType;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.eventId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PokemonEvent other = (PokemonEvent) obj;
        if (!Objects.equals(this.eventId, other.eventId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PokemonEvent{" + "eventId=" + eventId + ", eventType=" + eventType + '}';
    }

}
