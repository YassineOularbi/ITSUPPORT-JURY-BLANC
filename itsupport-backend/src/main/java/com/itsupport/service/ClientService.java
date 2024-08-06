package com.itsupport.service;

import com.itsupport.dto.UserUpdateDto;
import com.itsupport.exception.ClientNotFoundException;
import com.itsupport.mapper.ClientMapper;
import com.itsupport.model.Client;
import com.itsupport.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    public List<Client> getAllClients() {
        var clients = clientRepository.findAll();
        if (clients.isEmpty()) {
            throw new ClientNotFoundException();
        }
        return clients;
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
    }

    public Client updateClient(Long id, UserUpdateDto userUpdateDto) {
        var client = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
        var updatedClient = clientMapper.partialUpdate(userUpdateDto, client);
        return clientRepository.save(updatedClient);
    }

    public void deleteClient(Long id) {
        var client = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
        clientRepository.delete(client);
    }
}
