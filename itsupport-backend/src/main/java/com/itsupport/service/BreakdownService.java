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

@Service
@Transactional
@RequiredArgsConstructor
public class BreakdownService {

    private final BreakdownRepository breakdownRepository;

    private final BreakdownMapper breakdownMapper;

    public Breakdown createBreakdown(BreakdownDto breakdownDto) {
        var breakdown = breakdownMapper.toEntity(breakdownDto);
        return breakdownRepository.save(breakdown);
    }

    public List<Breakdown> getAllBreakdowns() {
        var breakdowns = breakdownRepository.findAll();
        if (breakdowns.isEmpty()) {
            throw new BreakdownNotFoundException();
        }
        return breakdowns;
    }

    public Breakdown getBreakdownById(Long id) {
        return breakdownRepository.findById(id).orElseThrow(BreakdownNotFoundException::new);
    }

    public Breakdown updateBreakdown(Long id, BreakdownDto breakdownDto) {
        var breakdown = breakdownRepository.findById(id).orElseThrow(BreakdownNotFoundException::new);
        var updatedBreakdown = breakdownMapper.partialUpdate(breakdownDto, breakdown);
        return breakdownRepository.save(updatedBreakdown);
    }

    public void deleteBreakdown(Long id) {
        var breakdown = breakdownRepository.findById(id).orElseThrow(BreakdownNotFoundException::new);
        breakdownRepository.delete(breakdown);
    }
}
