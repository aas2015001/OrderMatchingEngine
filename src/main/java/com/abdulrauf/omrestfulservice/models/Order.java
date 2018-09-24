package com.abdulrauf.omrestfulservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Order {
    @JsonIgnore
    private UUID id;
    @JsonIgnore
    private LocalDateTime currentTime;
    private long qty;
    private double prc;
    @JsonIgnore
    private final OrderType type;

    public Order(OrderType type) {
        this.id = UUID.randomUUID();
        this.currentTime = LocalDateTime.now();
        this.type = type;
    }

    public Order(long qty, double prc, OrderType type) {
        this.id = UUID.randomUUID();
        this.currentTime = LocalDateTime.now();
        this.qty = qty;
        this.prc = prc;
        this.type = type;
    }

    public Order(UUID id, LocalDateTime currentTime, long qty, double prc, OrderType type) {
        this.id = id;
        this.currentTime = currentTime;
        this.qty = qty;
        this.prc = prc;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public LocalDateTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(LocalDateTime currentTime) {
        this.currentTime = currentTime;
    }

    public long getQty() {
        return qty;
    }

    public void setQty(long qty) {
        this.qty = qty;
    }

    public double getPrc() {
        return prc;
    }

    public void setPrc(double prc) {
        this.prc = prc;
    }

    public OrderType getType() {
        return type;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + getId().hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((obj == null) || (obj.getClass() != this.getClass()))
            return false;

        Order order = (Order) obj;
        return getId().equals(order.getId());
    }

    @Override
    public String toString() {
        //return "qty: " + getQty() + " prc: " + getPrc() + " id: " + getId() + " date: " + getCurrentTime() + " type: " + getType();
        return "qty: " + getQty() + " prc: " + getPrc();
    }
}