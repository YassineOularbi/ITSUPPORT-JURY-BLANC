package com.itsupport.service;

import com.itsupport.dto.EquipmentDto;
import com.itsupport.enums.EquipmentStatus;
import com.itsupport.exception.ClientNotFoundException;
import com.itsupport.exception.EquipmentNotFoundException;
import com.itsupport.mapper.EquipmentMapper;
import com.itsupport.model.Equipment;
import com.itsupport.repository.ClientRepository;
import com.itsupport.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for managing {@link Equipment} entities.
 *
 * The EquipmentService class provides business logic and operations related to {@link Equipment} entities.
 * It interacts with {@link EquipmentRepository} for data access, and {@link EquipmentMapper} to map data between
 * {@link EquipmentDto} and {@link Equipment}. This service handles operations such as creating, retrieving, updating,
 * deleting, and assigning equipment to clients.
 *
 * Dependencies:
 *
 * - {@link EquipmentRepository} - The repository used for CRUD operations on {@link Equipment} entities.
 * - {@link EquipmentMapper} - The mapper used for converting between {@link EquipmentDto} and {@link Equipment}.
 * - {@link ClientRepository} - The repository used for CRUD operations on {@link @Client} entities.
 *
 * Methods:
 *
 * - {@link #createEquipment(EquipmentDto)} - Creates a new {@link Equipment} entity from the provided {@link EquipmentDto}
 *   and sets its status to {@link EquipmentStatus#AVAILABLE}.
 * - {@link #getAllEquipments()} - Retrieves a list of all {@link Equipment} entities. Throws {@link EquipmentNotFoundException}
 *   if no equipment is found.
 * - {@link #getEquipmentById(Long)} - Retrieves an {@link Equipment} entity by its ID. Throws {@link EquipmentNotFoundException}
 *   if the equipment with the specified ID is not found.
 * - {@link #updateEquipment(Long, EquipmentDto)} - Updates an existing {@link Equipment} entity with data from an
 *   {@link EquipmentDto}. Throws {@link EquipmentNotFoundException} if the equipment is not found.
 * - {@link #deleteEquipment(Long)} - Deletes an {@link Equipment} entity by its ID. Throws {@link EquipmentNotFoundException}
 *   if the equipment is not found.
 * - {@link #assignEquipmentToClient(Long, Long)} - Assigns a specific {@link Equipment} to a {@link @Client} by their IDs
 *   and updates the equipment's status to {@link EquipmentStatus#IN_SERVICE}. Throws {@link EquipmentNotFoundException}
 *   if the equipment is not found, and {@link ClientNotFoundException} if the client is not found.
 * - {@link #getAllEquipmentOutService()} - Retrieves a list of {@link Equipment} entities that are out of service.
 *   Throws {@link EquipmentNotFoundException} if no equipment is found.
 * - {@link #getEquipmentsByClient(Long)} - Retrieves a list of {@link Equipment} entities assigned to a specific {@link @Client}
 *   by their ID. Throws {@link EquipmentNotFoundException} if no equipment is found for the client.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 *
 * @see com.itsupport.model.Equipment
 * @see com.itsupport.dto.EquipmentDto
 * @see com.itsupport.mapper.EquipmentMapper
 * @see com.itsupport.repository.EquipmentRepository
 * @see com.itsupport.repository.ClientRepository
 * @see com.itsupport.enums.EquipmentStatus
 * @see com.itsupport.exception.EquipmentNotFoundException
 * @see com.itsupport.exception.ClientNotFoundException
 */
@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final EquipmentMapper equipmentMapper;
    private final ClientRepository clientRepository;

    /**
     * Creates a new {@link Equipment} entity with the status set to {@link EquipmentStatus#AVAILABLE}.
     *
     * @param equipmentDto the data transfer object containing equipment details
     * @return the created {@link Equipment} entity
     */
    public Equipment createEquipment(EquipmentDto equipmentDto) {
        var equipment = equipmentMapper.toEntity(equipmentDto);
        equipment.setStatus(EquipmentStatus.AVAILABLE);
        return equipmentRepository.save(equipment);
    }

    /**
     * Retrieves a list of all {@link Equipment} entities.
     *
     * @return a list of {@link Equipment} entities
     * @throws EquipmentNotFoundException if no equipment is found in the database
     */
    public List<Equipment> getAllEquipments() {
        var equipments = equipmentRepository.findAll();
        if (equipments.isEmpty()){
            throw new EquipmentNotFoundException();
        }
        return equipments;
    }

    /**
     * Retrieves an {@link Equipment} entity by its ID.
     *
     * @param id the ID of the equipment to be retrieved
     * @return the {@link Equipment} entity with the specified ID
     * @throws EquipmentNotFoundException if the equipment with the specified ID is not found
     */
    public Equipment getEquipmentById(Long id) {
        return equipmentRepository.findById(id).orElseThrow(EquipmentNotFoundException::new);
    }

    /**
     * Updates an existing {@link Equipment} entity with data from an {@link EquipmentDto}.
     *
     * @param id the ID of the equipment to be updated
     * @param equipmentDto the data transfer object containing the update information
     * @return the updated {@link Equipment} entity
     * @throws EquipmentNotFoundException if the equipment with the specified ID is not found
     */
    public Equipment updateEquipment(Long id, EquipmentDto equipmentDto) {
        var equipment = equipmentRepository.findById(id).orElseThrow(EquipmentNotFoundException::new);
        var updatedEquipment = equipmentMapper.partialUpdate(equipmentDto, equipment);
        return equipmentRepository.save(updatedEquipment);
    }

    /**
     * Deletes an {@link Equipment} entity by its ID.
     *
     * @param id the ID of the equipment to be deleted
     * @throws EquipmentNotFoundException if the equipment with the specified ID is not found
     */
    public void deleteEquipment(Long id) {
        var equipment = equipmentRepository.findById(id).orElseThrow(EquipmentNotFoundException::new);
        equipmentRepository.delete(equipment);
    }

    /**
     * Assigns a specific {@link Equipment} to a {@link @Client} by their IDs and updates the equipment's status
     * to {@link EquipmentStatus#IN_SERVICE}.
     *
     * @param equipmentId the ID of the equipment to be assigned
     * @param clientId the ID of the client to whom the equipment is assigned
     * @return the updated {@link Equipment} entity
     * @throws EquipmentNotFoundException if the equipment with the specified ID is not found
     * @throws ClientNotFoundException if the client with the specified ID is not found
     */
    public Equipment assignEquipmentToClient(Long equipmentId, Long clientId){
        var equipment = equipmentRepository.findById(equipmentId).orElseThrow(EquipmentNotFoundException::new);
        var client = clientRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
        equipment.setClient(client);
        equipment.setStatus(EquipmentStatus.IN_SERVICE);
        return equipmentRepository.save(equipment);
    }

    /**
     * Retrieves a list of {@link Equipment} entities that are out of service.
     *
     * @return a list of {@link Equipment} entities with status {@link EquipmentStatus#OUT_OF_SERVICE}
     * @throws EquipmentNotFoundException if no equipment with the status OUT_OF_SERVICE is found
     */
    public List<Equipment> getAllEquipmentOutService(){
        var equipments = equipmentRepository.getEquipmentByStatus(EquipmentStatus.OUT_OF_SERVICE);
        if (equipments.isEmpty()){
            throw new EquipmentNotFoundException();
        }
        return equipments;
    }

    /**
     * Retrieves a list of {@link Equipment} entities assigned to a specific {@link @Client}.
     *
     * @param id the ID of the client whose equipment is to be retrieved
     * @return a list of {@link Equipment} entities assigned to the client
     * @throws EquipmentNotFoundException if no equipment is found for the specified client
     */
    public List<Equipment> getEquipmentsByClient(Long id){
        var equipments = equipmentRepository.getEquipmentByClientId(id);
        if (equipments.isEmpty()){
            throw new EquipmentNotFoundException();
        }
        return equipments;
    }
}
