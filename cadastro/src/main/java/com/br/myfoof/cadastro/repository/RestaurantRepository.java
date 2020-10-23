package com.br.myfoof.cadastro.repository;

import com.br.myfoof.cadastro.entity.Client;
import com.br.myfoof.cadastro.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
