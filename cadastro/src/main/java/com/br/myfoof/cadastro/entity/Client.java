package com.br.myfoof.cadastro.entity;

import com.br.myfoof.cadastro.dto.ClientDTO;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.ui.ModelMap;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String email;
    private String password;


    // utilizando o modelMapper para transforma/mapear uma classe em outro tipo de classe
    public static Client create(ClientDTO clientDTO){
        return new ModelMapper().map(clientDTO, Client.class);
    }

}
