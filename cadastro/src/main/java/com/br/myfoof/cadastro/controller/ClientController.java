package com.br.myfoof.cadastro.controller;

import com.br.myfoof.cadastro.dto.ClientDTO;
import com.br.myfoof.cadastro.entity.Client;
import com.br.myfoof.cadastro.repository.ClientRepository;
import com.br.myfoof.cadastro.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;
    private final ClientRepository clientRepository;

    @Autowired
    public ClientController(ClientService clientService, ClientRepository clientRepository) {
        this.clientService = clientService;
        this.clientRepository = clientRepository;
    }

    @PostMapping()
    public ResponseEntity insertClient(@RequestBody ClientDTO clientDTO){
        try {
            return ResponseEntity.ok(clientService.insert(Client.create(clientDTO)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity updateClient(@PathVariable("id") Long id, @RequestBody ClientDTO clientDTO ){
            Client client = Client.create(clientDTO);
            client.setId(id);
            Client clientUpdate = clientService.update(client);
            return Objects.nonNull(clientUpdate) ? ResponseEntity.ok(clientUpdate) : ResponseEntity.notFound().build() ;
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteClient(@PathVariable("id") Long id) {
       return clientService.delete(id) ? ResponseEntity.ok().build() :  ResponseEntity.notFound().build();
    }

    @GetMapping("{id}")
    public ResponseEntity show(@PathVariable("id") Long id){
        Optional<Client> client = clientService.show(id);
        return client.isPresent() ? ResponseEntity.ok(client) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Client> index(Client filtro) {
        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, exampleMatcher);

        return clientRepository.findAll(example);
    }
}
