package com.br.myfoof.cadastro.service;

import com.br.myfoof.cadastro.entity.Client;
import com.br.myfoof.cadastro.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client insert(Client client) {
        return this.clientRepository.save(client);
    }

    public Client update(Client client) {
        Optional<Client> newClient = clientRepository.findById(client.getId());
        if (newClient.isPresent()) {
            return clientRepository.save(client);
        } else {
            return null;
        }
    }

    public Boolean delete(Long id){
        Optional<Client> client = clientRepository.findById(id);
        if(client.isPresent()){
            clientRepository.delete(client.get());
            return true;
        }else{
            return false;
        }
    }
    public Optional<Client> show(Long id){
        return clientRepository.findById(id);
    }

}
