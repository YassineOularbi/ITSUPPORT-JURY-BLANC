package com.itsupport.service;

import com.itsupport.dto.BreakdownDto;
import com.itsupport.exception.BreakdownNotFoundException;
import com.itsupport.mapper.BreakdownMapper;
import com.itsupport.model.Breakdown;
import com.itsupport.repository.BreakdownRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for managing {@link Breakdown} entities.
 *
 * The BreakdownService class provides business logic and operations related to {@link Breakdown} entities. It utilizes
 * {@link BreakdownRepository} for database interactions and {@link BreakdownMapper} for converting between {@link BreakdownDto}
 * and {@link Breakdown}. This service manages the lifecycle of breakdowns, including creation, retrieval, update, and deletion.
 *
 * Dependencies:
 *
 * - {@link BreakdownRepository} - The repository used for CRUD operations on {@link Breakdown} entities.
 * - {@link BreakdownMapper} - The mapper used for converting between {@link BreakdownDto} and {@link Breakdown}.
 *
 * Methods:
 *
 * - {@link #createBreakdown(BreakdownDto)} - Creates a new {@link Breakdown} entity from the provided {@link BreakdownDto} and saves it to the database.
 * - {@link #getAllBreakdowns()} - Retrieves a list of all {@link Breakdown} entities. Throws {@link BreakdownNotFoundException} if no breakdowns are found.
 * - {@link #getBreakdownById(Long)} - Retrieves a {@link Breakdown} entity by its ID. Throws {@link BreakdownNotFoundException} if the breakdown is not found.
 * - {@link #updateBreakdown(Long, BreakdownDto)} - Updates an existing {@link Breakdown} entity with data from a {@link BreakdownDto}. Throws {@link BreakdownNotFoundException} if the breakdown is not found.
 * - {@link #deleteBreakdown(Long)} - Deletes a {@link Breakdown} entity by its ID. Throws {@link BreakdownNotFoundException} if the breakdown is not found.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 *
 * @see com.itsupport.model.Breakdown
 * @see com.itsupport.repository.BreakdownRepository
 * @see com.itsupport.mapper.BreakdownMapper
 * @see com.itsupport.dto.BreakdownDto
 * @see com.itsupport.exception.BreakdownNotFoundException
 */
@Service
@Transactional
@RequiredArgsConstructor
public class BreakdownService {

    private final BreakdownRepository breakdownRepository;
    private final BreakdownMapper breakdownMapper;

    /**
     * Creates a new {@link Breakdown} entity from the provided {@link BreakdownDto} and saves it to the database.
     *
     * @param breakdownDto the data transfer object containing information to create the breakdown
     * @return the created {@link Breakdown} entity
     */
    public Breakdown createBreakdown(BreakdownDto breakdownDto) {
        var breakdown = breakdownMapper.toEntity(breakdownDto);
        return breakdownRepository.save(breakdown);
    }

    /**
     * Retrieves a list of all {@link Breakdown} entities.
     *
     * @return a list of {@link Breakdown} entities
     * @throws BreakdownNotFoundException if no breakdowns are found in the database
     */
    public List<Breakdown> getAllBreakdowns() {
        var breakdowns = breakdownRepository.findAll();
        if (breakdowns.isEmpty()) {
            throw new BreakdownNotFoundException();
        }
        return breakdowns;
    }

    /**
     * Retrieves a {@link Breakdown} entity by its ID.
     *
     * @param id the ID of the breakdown to be retrieved
     * @return the {@link Breakdown} entity with the specified ID
     * @throws BreakdownNotFoundException if the breakdown with the specified ID is not found
     */
    public Breakdown getBreakdownById(Long id) {
        return breakdownRepository.findById(id).orElseThrow(BreakdownNotFoundException::new);
    }

    /**
     * Updates an existing {@link Breakdown} entity with data from a {@link BreakdownDto}.
     *
     * @param id the ID of the breakdown to be updated
     * @param breakdownDto the data transfer object containing the update information
     * @return the updated {@link Breakdown} entity
     * @throws BreakdownNotFoundException if the breakdown with the specified ID is not found
     */
    public Breakdown updateBreakdown(Long id, BreakdownDto breakdownDto) {
        var breakdown = breakdownRepository.findById(id).orElseThrow(BreakdownNotFoundException::new);
        var updatedBreakdown = breakdownMapper.partialUpdate(breakdownDto, breakdown);
        return breakdownRepository.save(updatedBreakdown);
    }

    /**
     * Deletes a {@link Breakdown} entity by its ID.
     *
     * @param id the ID of the breakdown to be deleted
     * @throws BreakdownNotFoundException if the breakdown with the specified ID is not found
     */
    public void deleteBreakdown(Long id) {
        var breakdown = breakdownRepository.findById(id).orElseThrow(BreakdownNotFoundException::new);
        breakdownRepository.delete(breakdown);
    }
}
