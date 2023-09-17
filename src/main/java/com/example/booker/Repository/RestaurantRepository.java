package com.example.booker.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.booker.Model.Restaurant;
import java.util.Optional;


@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant,Integer>
{
    Optional<Restaurant> findByResSecret(String resSecret);;
}
