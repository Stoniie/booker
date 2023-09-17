package com.example.booker.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.booker.Model.User;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> 
{
    Optional<User> findBySecret(String secret);
    Optional<User> findByEmailAndPassword(String email, String password);
}
