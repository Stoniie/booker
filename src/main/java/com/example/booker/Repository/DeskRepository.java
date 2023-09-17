package com.example.booker.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.booker.Model.Desk;

@Repository
public interface DeskRepository extends CrudRepository<Desk,Integer>{
    
}
