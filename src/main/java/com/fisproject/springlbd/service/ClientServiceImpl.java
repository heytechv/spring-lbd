package com.fisproject.springlbd.service;

import com.fisproject.springlbd.entity.Client;
import com.fisproject.springlbd.entity.Project;
import com.fisproject.springlbd.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class ClientServiceImpl {

    private ClientRepository clientRepository;


    private Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));
    }


    public void addNew(Client client) {
        clientRepository.save(client);
    }

    @Transactional
    public void addProject(Long id, Project project) {
        Client client = findById(id);
        client.addProject(project);

    }





}
