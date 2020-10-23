package com.br.myfoof.cadastro.service;

import com.br.myfoof.cadastro.entity.Client;
import com.br.myfoof.cadastro.entity.Restaurant;
import com.br.myfoof.cadastro.repository.ClientRepository;
import com.br.myfoof.cadastro.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant insert(Restaurant restaurant) {
        return this.restaurantRepository.save(restaurant);
    }

    public Restaurant update(Restaurant restaurant) {
        Optional<Restaurant> newRestaurant = restaurantRepository.findById(restaurant.getId());
        if (newRestaurant.isPresent()) {
            return restaurantRepository.save(restaurant);
        } else {
            return null;
        }
    }

    public Boolean delete(Long id){
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if(restaurant.isPresent()){
            restaurantRepository.delete(restaurant.get());
            return true;
        }else{
            return false;
        }
    }
    public Optional<Restaurant> show(Long id){
        return restaurantRepository.findById(id);
    }

}
