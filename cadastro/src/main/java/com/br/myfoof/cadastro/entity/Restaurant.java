package com.br.myfoof.cadastro.entity;

import com.br.myfoof.cadastro.dto.ClientDTO;
import com.br.myfoof.cadastro.dto.RestaurantDTO;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;


    // utilizando o modelMapper para transforma/mapear uma classe em outro tipo de classe
    public static Restaurant create(RestaurantDTO restaurantDTO){
        return new ModelMapper().map(restaurantDTO, Restaurant.class);
    }

}
