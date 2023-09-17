package com.example.booker.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Desk 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deskId;

    @Column()
    @NotNull
    private Integer seat;

    private boolean isRes;
    private String description;

    
    @ManyToOne
    @JoinColumn(name="RES_ID")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    
    public Desk() {
    }

    public Desk(Integer deskId, @NotNull Integer seat, boolean isRes, String description, Restaurant restaurant,
            User user) {
        this.deskId = deskId;
        this.seat = seat;
        this.isRes = isRes;
        this.description = description;
        this.restaurant = restaurant;
        this.user = user;
    }

    



    public Desk(Integer deskId, @NotNull Integer seat) {
        this.deskId = deskId;
        this.seat = seat;
    }

    public Integer getDeskId() {
        return deskId;
    }
    public void setDeskId(Integer deskId) {
        this.deskId = deskId;
    }
    public Integer getSeat() {return seat;}
    public void setSeat(Integer seat) {this.seat = seat;}
    public boolean getIsRes() {return isRes;}
    @JsonIgnore
    public void setIsRes(boolean isRes) {this.isRes = isRes;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public Restaurant getRestaurant() {
        return restaurant;
    }
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    
    
    
}
