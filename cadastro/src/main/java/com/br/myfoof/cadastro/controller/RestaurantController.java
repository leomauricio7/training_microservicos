package com.br.myfoof.cadastro.controller;

import com.br.myfoof.cadastro.dto.RestaurantDTO;
import com.br.myfoof.cadastro.entity.Restaurant;
import com.br.myfoof.cadastro.repository.RestaurantRepository;
import com.br.myfoof.cadastro.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, RestaurantRepository restaurantRepository) {
        this.restaurantService = restaurantService;
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping()
    public ResponseEntity insert(@RequestBody RestaurantDTO restaurantDTO){
        try {
            return ResponseEntity.ok(restaurantService.insert(Restaurant.create(restaurantDTO)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody RestaurantDTO restaurantDTO ){
        Restaurant restaurant = Restaurant.create(restaurantDTO);
            restaurant.setId(id);
            Restaurant restaurantUpdate = restaurantService.update(restaurant);
            return Objects.nonNull(restaurantUpdate) ? ResponseEntity.ok(restaurantUpdate) : ResponseEntity.notFound().build() ;
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
       return restaurantService.delete(id) ? ResponseEntity.ok().build() :  ResponseEntity.notFound().build();
    }

    @GetMapping("{id}")
    public ResponseEntity show(@PathVariable("id") Long id){
        Optional<Restaurant> restaurant = restaurantService.show(id);
        return restaurant.isPresent() ? ResponseEntity.ok(restaurant) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Restaurant> index(Restaurant filtro) {
        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, exampleMatcher);

        return restaurantRepository.findAll(example);
    }

}
