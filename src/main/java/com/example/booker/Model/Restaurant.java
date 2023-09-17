package com.example.booker.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Restaurant 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RES_ID")
    private Integer resId;

    @Column()
    @NotBlank
    private String name;

    @Column()
    @NotNull
    private Integer capacity;

    @Column(unique = true)
    @NotBlank 
    private String email;

    @Column()
    @NotBlank
    private String city;

    @Column()
    @NotBlank
    private String state;

    @Column()
    @NotBlank
    private String password;

    private String resSecret;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant",
                            cascade = CascadeType.MERGE,
                            orphanRemoval = true,
                            fetch = FetchType.LAZY)
    private Set<Desk> desk = new HashSet<Desk>();



    public Restaurant() {
    }

    public Restaurant(Integer resId, @NotBlank String name, @NotNull Integer capacity, @NotBlank String email,
            @NotBlank String city, @NotBlank String state, @NotBlank String password, String resSecret,
            Set<Desk> desk) {
        this.resId = resId;
        this.name = name;
        this.capacity = capacity;
        this.email = email;
        this.city = city;
        this.state = state;
        this.password = password;
        this.resSecret = resSecret;
        this.desk = desk;
    }



    public Integer getResId() {
        return resId;
    }
    public void setResId(Integer resId) {
        this.resId = resId;
    }
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public Integer getCapacity() {return capacity;}
    public void setCapacity(Integer capacity) {this.capacity = capacity;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public String getCity() {return city;}
    public void setCity(String city) {this.city = city;}
    public String getState() {return state;}
    public void setState(String state) {this.state = state;}
    public String getResSecret() {
        return resSecret;
    }
    public void setResSecret(String resSecret) {
        this.resSecret = resSecret;
    }
    public Set<Desk> getDesk() {
        return desk;
    }
    public void setDesk(Set<Desk> desk) {
        this.desk = desk;
    }
    
   

    


    
    
}
