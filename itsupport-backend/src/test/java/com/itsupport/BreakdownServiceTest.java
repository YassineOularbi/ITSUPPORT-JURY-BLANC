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

class BreakdownServiceTest {

    @Mock
    private BreakdownRepository breakdownRepository;

    @Mock
    private BreakdownMapper breakdownMapper;

    @InjectMocks
    private BreakdownService breakdownService;

    private Breakdown breakdown;
    private BreakdownDto breakdownDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        breakdown = new Breakdown();
        breakdown.setId(1L);
    }

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

    @Test
    void getAllBreakdowns_ShouldReturnListOfBreakdowns() {
        when(breakdownRepository.findAll()).thenReturn(List.of(breakdown));

        List<Breakdown> breakdowns = breakdownService.getAllBreakdowns();

        assertNotNull(breakdowns);
        assertFalse(breakdowns.isEmpty());
        assertEquals(1, breakdowns.size());
        verify(breakdownRepository, times(1)).findAll();
    }

    @Test
    void getAllBreakdowns_NoBreakdownsFound() {
        when(breakdownRepository.findAll()).thenReturn(List.of());

        assertThrows(BreakdownNotFoundException.class, () -> {
            breakdownService.getAllBreakdowns();
        });
        verify(breakdownRepository, times(1)).findAll();
    }

    @Test
    void getBreakdownById_ShouldReturnBreakdown() {
        Long id = 1L;
        when(breakdownRepository.findById(id)).thenReturn(Optional.of(breakdown));

        Breakdown foundBreakdown = breakdownService.getBreakdownById(id);

        assertNotNull(foundBreakdown);
        assertEquals(id, foundBreakdown.getId());
        verify(breakdownRepository, times(1)).findById(id);
    }

    @Test
    void getBreakdownById_BreakdownNotFound() {
        Long id = 1L;
        when(breakdownRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(BreakdownNotFoundException.class, () -> {
            breakdownService.getBreakdownById(id);
        });
        verify(breakdownRepository, times(1)).findById(id);
    }

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

    @Test
    void deleteBreakdown_ShouldDeleteBreakdown() {
        Long id = 1L;
        when(breakdownRepository.findById(id)).thenReturn(Optional.of(breakdown));

        breakdownService.deleteBreakdown(id);

        verify(breakdownRepository, times(1)).findById(id);
        verify(breakdownRepository, times(1)).delete(breakdown);
    }

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
