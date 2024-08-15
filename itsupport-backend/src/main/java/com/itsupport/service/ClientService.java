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

/**
 * Service class for managing {@link Client} entities.
 *
 * The ClientService class provides business logic and operations related to {@link Client} entities. It uses {@link ClientRepository}
 * for data access and {@link ClientMapper} to map data between {@link UserUpdateDto} and {@link Client}. This service handles operations
 * such as retrieving, updating, and deleting clients.
 *
 * Dependencies:
 *
 * - {@link ClientRepository} - The repository used for CRUD operations on {@link Client} entities.
 * - {@link ClientMapper} - The mapper used for converting between {@link UserUpdateDto} and {@link Client}.
 *
 * Methods:
 *
 * - {@link #getAllClients()} - Retrieves a list of all {@link Client} entities. Throws {@link ClientNotFoundException} if no clients are found.
 * - {@link #getClientById(Long)} - Retrieves a {@link Client} entity by its ID. Throws {@link ClientNotFoundException} if the client is not found.
 * - {@link #updateClient(Long, UserUpdateDto)} - Updates an existing {@link Client} entity with data from a {@link UserUpdateDto}. Throws {@link ClientNotFoundException} if the client is not found.
 * - {@link #deleteClient(Long)} - Deletes a {@link Client} entity by its ID. Throws {@link ClientNotFoundException} if the client is not found.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 *
 * @see com.itsupport.model.Client
 * @see com.itsupport.repository.ClientRepository
 * @see com.itsupport.mapper.ClientMapper
 * @see com.itsupport.dto.UserUpdateDto
 * @see com.itsupport.exception.ClientNotFoundException
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final FileStorageService fileStorageService;

    /**
     * Retrieves a list of all {@link Client} entities.
     *
     * @return a list of {@link Client} entities
     * @throws ClientNotFoundException if no clients are found in the database
     */
    public List<Client> getAllClients() {
        var clients = clientRepository.findAll();
        if (clients.isEmpty()) {
            throw new ClientNotFoundException();
        }
        return clients;
    }

    /**
     * Retrieves a {@link Client} entity by its ID.
     *
     * @param id the ID of the client to be retrieved
     * @return the {@link Client} entity with the specified ID
     * @throws ClientNotFoundException if the client with the specified ID is not found
     */
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
    }

    /**
     * Updates an existing {@link Client} entity with data from a {@link UserUpdateDto}.
     *
     * @param id the ID of the client to be updated
     * @param userUpdateDto the data transfer object containing the update information
     * @return the updated {@link Client} entity
     * @throws ClientNotFoundException if the client with the specified ID is not found
     */
    public Client updateClient(Long id, UserUpdateDto userUpdateDto) {
        var client = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
        var updatedClient = clientMapper.partialUpdate(userUpdateDto, client);
        if (userUpdateDto.getPicture() != null && !userUpdateDto.getPicture().isEmpty()) {
            String fileName = fileStorageService.storeFile(userUpdateDto.getPicture());
            updatedClient.setAvatarUrl(fileName);
        }
        return clientRepository.save(updatedClient);
    }

    /**
     * Deletes a {@link Client} entity by its ID.
     *
     * @param id the ID of the client to be deleted
     * @throws ClientNotFoundException if the client with the specified ID is not found
     */
    public void deleteClient(Long id) {
        var client = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
        clientRepository.delete(client);
    }
}
