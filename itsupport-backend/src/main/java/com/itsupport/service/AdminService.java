package com.itsupport.service;

import com.itsupport.dto.UserUpdateDto;
import com.itsupport.exception.AdminNotFoundException;
import com.itsupport.mapper.AdminMapper;
import com.itsupport.model.Admin;
import com.itsupport.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    private final AdminMapper adminMapper;

    public List<Admin> getAllAdmins(){
        var admins = adminRepository.findAll();
        if (admins.isEmpty()){
            throw new AdminNotFoundException();
        }
        return admins;
    }

    public Admin getAdminById(Long id){
        return adminRepository.findById(id).orElseThrow(AdminNotFoundException::new);
    }

    public Admin updateAdmin(Long id, UserUpdateDto userUpdateDto){
        var admin = adminRepository.findById(id).orElseThrow(AdminNotFoundException::new);
        var updatedAdmin = adminMapper.partialUpdate(userUpdateDto, admin);
        return adminRepository.save(updatedAdmin);
    }

    public void deleteAdmin(Long id){
        var admin = adminRepository.findById(id).orElseThrow(AdminNotFoundException::new);
        adminRepository.delete(admin);
    }
}
