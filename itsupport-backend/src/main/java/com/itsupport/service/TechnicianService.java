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

@Service
@Transactional
@RequiredArgsConstructor
public class TechnicianService {

    private final TechnicianRepository technicianRepository;

    private final TechnicianMapper technicianMapper;

    public List<Technician> getAllTechnicians() {
        var technicians = technicianRepository.findAll();
        if (technicians.isEmpty()) {
            throw new TechnicianNotFoundException();
        }
        return technicians;
    }

    public Technician getTechnicianById(Long id) {
        return technicianRepository.findById(id).orElseThrow(TechnicianNotFoundException::new);
    }

    public Technician updateTechnician(Long id, UserUpdateDto userUpdateDto) {
        var technician = technicianRepository.findById(id).orElseThrow(TechnicianNotFoundException::new);
        var updatedTechnician = technicianMapper.partialUpdate(userUpdateDto, technician);
        return technicianRepository.save(updatedTechnician);
    }

    public void deleteTechnician(Long id) {
        var technician = technicianRepository.findById(id).orElseThrow(TechnicianNotFoundException::new);
        technicianRepository.delete(technician);
    }
}
