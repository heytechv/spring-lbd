package com.fisproject.springlbd.service;

import com.fisproject.springlbd.entity.Client;
import com.fisproject.springlbd.entity.Project;
import com.fisproject.springlbd.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl {

    private ClientRepository clientRepository;


    /** Private
     * */
    private Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found!"));
    }


    /** Public
     * */
    public void add(Client client) {
        if (client == null)
            throw new RuntimeException("Client cannot be null!");
        clientRepository.save(client);
    }

    public Client getById(Long id) {
        return findById(id);
    }

    public List<Project> getAllProjects(Long id) {
        return findById(id).getProjectList();
    }

    /** Add Project to Client by id */
    public void addProject(Long id, Project project) {
        Client client = findById(id);
        client.addProject(project);
        clientRepository.save(client);
    }




}
