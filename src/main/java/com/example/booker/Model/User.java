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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column()
    @NotBlank
    private String firstname;

    @Column()
    @NotBlank
    private String surname;

    @Column()
    @NotBlank
    private String city;

    @Column()
    @NotBlank
    private String state;

    @Column(unique = true)
    @NotBlank
    private String email;

    @Column()
    @NotBlank
    private String password;

    private String secret;

    @JsonIgnore
    @OneToMany(mappedBy = "user", 
                        cascade = CascadeType.MERGE,
                        orphanRemoval = true,
                        fetch = FetchType.LAZY)
    private Set<Desk> desk = new HashSet<Desk>();



    public User()
    {}






    public User(Integer userId, @NotBlank String firstname, @NotBlank String surname, @NotBlank String city,
            @NotBlank String state, @NotBlank String email, @NotBlank String password, String secret, Set<Desk> desk) {
        this.userId = userId;
        this.firstname = firstname;
        this.surname = surname;
        this.city = city;
        this.state = state;
        this.email = email;
        this.password = password;
        this.secret = secret;
        this.desk = desk;
    }



    public User(@NotBlank String email, @NotBlank String password) {
        this.email = email;
        this.password = password;
    }



    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }   
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getPassword() { return password;}
    public void setPassword(String password) {this.password = password;}
    public String getSecret() {return secret;}
    @JsonIgnore
    public void setSecret(String secret) {this.secret = secret;}
    public String getFirstname() {return firstname;}
    public void setFirstname(String firstname) {this.firstname = firstname;}
    public String getSurname() {return surname;}
    public void setSurname(String surname) {this.surname = surname;}
    public String getCity() {return city;}
    public void setCity(String city) {this.city = city;}
    public String getState() {return state;}
    public void setState(String state) {this.state = state;}
    public Set<Desk> getDesk() {
        return desk;
    }
    public void setDesk(Set<Desk> desk) {
        this.desk = desk;
    }


    
}
