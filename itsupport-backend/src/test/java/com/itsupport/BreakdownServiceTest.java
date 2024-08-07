package com.itsupport;

import com.itsupport.dto.BreakdownDto;
import com.itsupport.exception.BreakdownNotFoundException;
import com.itsupport.mapper.BreakdownMapper;
import com.itsupport.model.Breakdown;
import com.itsupport.repository.BreakdownRepository;
import com.itsupport.service.BreakdownService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link BreakdownService}.
 *
 * This class contains unit tests for the {@link BreakdownService} class. The tests cover various aspects
 * of the service methods to ensure they behave correctly in different scenarios.
 *
 * Dependencies:
 *
 * - {@link BreakdownRepository} - The repository used for CRUD operations on {@link Breakdown} entities.
 * - {@link BreakdownMapper} - The mapper used for converting between {@link BreakdownDto} and {@link Breakdown}.
 *
 * Test Methods:
 *
 * - {@link #createBreakdown_ShouldCreateAndReturnBreakdown()} - Tests the creation of a new breakdown.
 * - {@link #getAllBreakdowns_ShouldReturnListOfBreakdowns()} - Tests retrieval of all breakdowns.
 * - {@link #getAllBreakdowns_NoBreakdownsFound()} - Tests retrieval of all breakdowns when none are found.
 * - {@link #getBreakdownById_ShouldReturnBreakdown()} - Tests retrieval of a breakdown by its ID.
 * - {@link #getBreakdownById_BreakdownNotFound()} - Tests retrieval of a breakdown by ID when not found.
 * - {@link #updateBreakdown_ShouldUpdateAndReturnBreakdown()} - Tests updating an existing breakdown.
 * - {@link #updateBreakdown_BreakdownNotFound()} - Tests updating a breakdown when it is not found.
 * - {@link #deleteBreakdown_ShouldDeleteBreakdown()} - Tests deletion of a breakdown.
 * - {@link #deleteBreakdown_BreakdownNotFound()} - Tests deletion of a breakdown when not found.
 */
class BreakdownServiceTest {

    @Mock
    private BreakdownRepository breakdownRepository;

    @Mock
    private BreakdownMapper breakdownMapper;

    @InjectMocks
    private BreakdownService breakdownService;

    private Breakdown breakdown;
    private BreakdownDto breakdownDto;

    /**
     * Initializes the test environment before each test.
     * Sets up mock objects and initializes test data.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        breakdown = new Breakdown();
        breakdown.setId(1L);

        breakdownDto = new BreakdownDto();
    }

    /**
     * Tests the creation of a new breakdown.
     * Verifies that the breakdown is created and returned as expected.
     */
    @Test
    void createBreakdown_ShouldCreateAndReturnBreakdown() {
        when(breakdownMapper.toEntity(breakdownDto)).thenReturn(breakdown);
        when(breakdownRepository.save(breakdown)).thenReturn(breakdown);

        Breakdown createdBreakdown = breakdownService.createBreakdown(breakdownDto);

        assertNotNull(createdBreakdown);
        assertEquals(breakdown.getId(), createdBreakdown.getId());
        verify(breakdownMapper, times(1)).toEntity(breakdownDto);
        verify(breakdownRepository, times(1)).save(breakdown);
    }

    /**
     * Tests retrieval of all breakdowns.
     * Verifies that a list of breakdowns is returned when there are breakdowns in the repository.
     */
    @Test
    void getAllBreakdowns_ShouldReturnListOfBreakdowns() {
        when(breakdownRepository.findAll()).thenReturn(List.of(breakdown));

        List<Breakdown> breakdowns = breakdownService.getAllBreakdowns();

        assertNotNull(breakdowns);
        assertFalse(breakdowns.isEmpty());
        assertEquals(1, breakdowns.size());
        verify(breakdownRepository, times(1)).findAll();
    }

    /**
     * Tests retrieval of all breakdowns when no breakdowns are found.
     * Verifies that a {@link BreakdownNotFoundException} is thrown.
     */
    @Test
    void getAllBreakdowns_NoBreakdownsFound() {
        when(breakdownRepository.findAll()).thenReturn(List.of());

        assertThrows(BreakdownNotFoundException.class, () -> {
            breakdownService.getAllBreakdowns();
        });
        verify(breakdownRepository, times(1)).findAll();
    }

    /**
     * Tests retrieval of a breakdown by its ID.
     * Verifies that the breakdown with the specified ID is returned.
     */
    @Test
    void getBreakdownById_ShouldReturnBreakdown() {
        Long id = 1L;
        when(breakdownRepository.findById(id)).thenReturn(Optional.of(breakdown));

        Breakdown foundBreakdown = breakdownService.getBreakdownById(id);

        assertNotNull(foundBreakdown);
        assertEquals(id, foundBreakdown.getId());
        verify(breakdownRepository, times(1)).findById(id);
    }

    /**
     * Tests retrieval of a breakdown by ID when the breakdown is not found.
     * Verifies that a {@link BreakdownNotFoundException} is thrown.
     */
    @Test
    void getBreakdownById_BreakdownNotFound() {
        Long id = 1L;
        when(breakdownRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(BreakdownNotFoundException.class, () -> {
            breakdownService.getBreakdownById(id);
        });
        verify(breakdownRepository, times(1)).findById(id);
    }

    /**
     * Tests updating an existing breakdown.
     * Verifies that the breakdown is updated and returned as expected.
     */
    @Test
    void updateBreakdown_ShouldUpdateAndReturnBreakdown() {
        Long id = 1L;
        BreakdownDto updatedDto = new BreakdownDto();
        Breakdown updatedBreakdown = new Breakdown();
        updatedBreakdown.setId(id);

        when(breakdownRepository.findById(id)).thenReturn(Optional.of(breakdown));
        when(breakdownMapper.partialUpdate(updatedDto, breakdown)).thenReturn(updatedBreakdown);
        when(breakdownRepository.save(updatedBreakdown)).thenReturn(updatedBreakdown);

        Breakdown result = breakdownService.updateBreakdown(id, updatedDto);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(breakdownRepository, times(1)).findById(id);
        verify(breakdownMapper, times(1)).partialUpdate(updatedDto, breakdown);
        verify(breakdownRepository, times(1)).save(updatedBreakdown);
    }

    /**
     * Tests updating a breakdown when it is not found.
     * Verifies that a {@link BreakdownNotFoundException} is thrown.
     */
    @Test
    void updateBreakdown_BreakdownNotFound() {
        Long id = 1L;
        BreakdownDto updatedDto = new BreakdownDto();

        when(breakdownRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(BreakdownNotFoundException.class, () -> {
            breakdownService.updateBreakdown(id, updatedDto);
        });
        verify(breakdownRepository, times(1)).findById(id);
        verify(breakdownMapper, never()).partialUpdate(any(), any());
        verify(breakdownRepository, never()).save(any());
    }

    /**
     * Tests deletion of a breakdown.
     * Verifies that the breakdown is deleted from the repository.
     */
    @Test
    void deleteBreakdown_ShouldDeleteBreakdown() {
        Long id = 1L;
        when(breakdownRepository.findById(id)).thenReturn(Optional.of(breakdown));

        breakdownService.deleteBreakdown(id);

        verify(breakdownRepository, times(1)).findById(id);
        verify(breakdownRepository, times(1)).delete(breakdown);
    }

    /**
     * Tests deletion of a breakdown when it is not found.
     * Verifies that a {@link BreakdownNotFoundException} is thrown.
     */
    @Test
    void deleteBreakdown_BreakdownNotFound() {
        Long id = 1L;
        when(breakdownRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(BreakdownNotFoundException.class, () -> {
            breakdownService.deleteBreakdown(id);
        });
        verify(breakdownRepository, times(1)).findById(id);
        verify(breakdownRepository, never()).delete(any());
    }
}
