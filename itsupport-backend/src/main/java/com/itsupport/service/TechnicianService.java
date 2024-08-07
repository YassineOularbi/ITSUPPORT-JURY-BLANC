package com.itsupport.service;

import com.itsupport.dto.UserUpdateDto;
import com.itsupport.exception.TechnicianNotFoundException;
import com.itsupport.mapper.TechnicianMapper;
import com.itsupport.model.Technician;
import com.itsupport.repository.TechnicianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for managing {@link Technician} entities.
 *
 * The TechnicianService class provides business logic and operations related to {@link Technician} entities.
 * It interacts with {@link TechnicianRepository} for data access, and {@link TechnicianMapper} to map data between
 * {@link UserUpdateDto} and {@link Technician}. This service handles operations such as retrieving, updating,
 * and deleting technicians.
 *
 * Dependencies:
 *
 * - {@link TechnicianRepository} - The repository used for CRUD operations on {@link Technician} entities.
 * - {@link TechnicianMapper} - The mapper used for converting between {@link UserUpdateDto} and {@link Technician}.
 *
 * Methods:
 *
 * - {@link #getAllTechnicians()} - Retrieves a list of all {@link Technician} entities. Throws {@link TechnicianNotFoundException}
 *   if no technicians are found.
 * - {@link #getAvailableTechnicians()} - Retrieves a list of {@link Technician} entities that are currently available.
 *   Throws {@link TechnicianNotFoundException} if no available technicians are found.
 * - {@link #getTechnicianById(Long)} - Retrieves a {@link Technician} entity by its ID. Throws {@link TechnicianNotFoundException}
 *   if the technician with the specified ID is not found.
 * - {@link #updateTechnician(Long, UserUpdateDto)} - Updates an existing {@link Technician} entity with data from a {@link UserUpdateDto}.
 *   Throws {@link TechnicianNotFoundException} if the technician with the specified ID is not found.
 * - {@link #deleteTechnician(Long)} - Deletes a {@link Technician} entity by its ID. Throws {@link TechnicianNotFoundException}
 *   if the technician with the specified ID is not found.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 *
 * @see com.itsupport.model.Technician
 * @see com.itsupport.dto.UserUpdateDto
 * @see com.itsupport.mapper.TechnicianMapper
 * @see com.itsupport.repository.TechnicianRepository
 * @see com.itsupport.exception.TechnicianNotFoundException
 */
@Service
@Transactional
@RequiredArgsConstructor
public class TechnicianService {

    private final TechnicianRepository technicianRepository;
    private final TechnicianMapper technicianMapper;

    /**
     * Retrieves a list of all {@link Technician} entities.
     *
     * @return a list of {@link Technician} entities
     * @throws TechnicianNotFoundException if no technicians are found in the database
     */
    public List<Technician> getAllTechnicians() {
        var technicians = technicianRepository.findAll();
        if (technicians.isEmpty()) {
            throw new TechnicianNotFoundException();
        }
        return technicians;
    }

    /**
     * Retrieves a list of {@link Technician} entities that are currently available.
     *
     * @return a list of available {@link Technician} entities
     * @throws TechnicianNotFoundException if no available technicians are found
     */
    public List<Technician> getAvailableTechnicians(){
        var technicians = technicianRepository.findTechniciansByAvailabilityIsTrue();
        if (technicians.isEmpty()) {
            throw new TechnicianNotFoundException();
        }
        return technicians;
    }

    /**
     * Retrieves a {@link Technician} entity by its ID.
     *
     * @param id the ID of the technician to be retrieved
     * @return the {@link Technician} entity with the specified ID
     * @throws TechnicianNotFoundException if the technician with the specified ID is not found
     */
    public Technician getTechnicianById(Long id) {
        return technicianRepository.findById(id).orElseThrow(TechnicianNotFoundException::new);
    }

    /**
     * Updates an existing {@link Technician} entity with data from a {@link UserUpdateDto}.
     *
     * @param id the ID of the technician to be updated
     * @param userUpdateDto the data transfer object containing the update information
     * @return the updated {@link Technician} entity
     * @throws TechnicianNotFoundException if the technician with the specified ID is not found
     */
    public Technician updateTechnician(Long id, UserUpdateDto userUpdateDto) {
        var technician = technicianRepository.findById(id).orElseThrow(TechnicianNotFoundException::new);
        var updatedTechnician = technicianMapper.partialUpdate(userUpdateDto, technician);
        return technicianRepository.save(updatedTechnician);
    }

    /**
     * Deletes a {@link Technician} entity by its ID.
     *
     * @param id the ID of the technician to be deleted
     * @throws TechnicianNotFoundException if the technician with the specified ID is not found
     */
    public void deleteTechnician(Long id) {
        var technician = technicianRepository.findById(id).orElseThrow(TechnicianNotFoundException::new);
        technicianRepository.delete(technician);
    }
}
