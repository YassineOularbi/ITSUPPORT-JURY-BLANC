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

/**
 * Service class for managing {@link Admin} entities.
 *
 * The AdminService class provides business logic and operations related to {@link Admin} entities. It uses {@link AdminRepository}
 * to interact with the database and {@link AdminMapper} to map data between entities and DTOs. This service handles operations such as
 * retrieving, updating, and deleting admins.
 *
 * Dependencies:
 *
 * - {@link AdminRepository} - The repository used for CRUD operations on {@link Admin} entities.
 * - {@link AdminMapper} - The mapper used for converting between {@link UserUpdateDto} and {@link Admin}.
 *
 * Methods:
 *
 * - {@link #getAllAdmins()} - Retrieves a list of all {@link Admin} entities. Throws {@link AdminNotFoundException} if no admins are found.
 * - {@link #getAdminById(Long)} - Retrieves an {@link Admin} entity by its ID. Throws {@link AdminNotFoundException} if the admin is not found.
 * - {@link #updateAdmin(Long, UserUpdateDto)} - Updates an existing {@link Admin} entity with data from a {@link UserUpdateDto}. Throws {@link AdminNotFoundException} if the admin is not found.
 * - {@link #deleteAdmin(Long)} - Deletes an {@link Admin} entity by its ID. Throws {@link AdminNotFoundException} if the admin is not found.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 *
 * @see com.itsupport.model.Admin
 * @see com.itsupport.repository.AdminRepository
 * @see com.itsupport.mapper.AdminMapper
 * @see com.itsupport.dto.UserUpdateDto
 * @see com.itsupport.exception.AdminNotFoundException
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final FileStorageService fileStorageService;

    /**
     * Retrieves a list of all {@link Admin} entities.
     *
     * @return a list of {@link Admin} entities
     * @throws AdminNotFoundException if no admins are found in the database
     */
    public List<Admin> getAllAdmins() {
        var admins = adminRepository.findAll();
        if (admins.isEmpty()) {
            throw new AdminNotFoundException();
        }
        return admins;
    }

    /**
     * Retrieves an {@link Admin} entity by its ID.
     *
     * @param id the ID of the admin to be retrieved
     * @return the {@link Admin} entity with the specified ID
     * @throws AdminNotFoundException if the admin with the specified ID is not found
     */
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).orElseThrow(AdminNotFoundException::new);
    }

    /**
     * Updates an existing {@link Admin} entity with data from a {@link UserUpdateDto}.
     *
     * @param id the ID of the admin to be updated
     * @param userUpdateDto the data transfer object containing the update information
     * @return the updated {@link Admin} entity
     * @throws AdminNotFoundException if the admin with the specified ID is not found
     */
    public Admin updateAdmin(Long id, UserUpdateDto userUpdateDto) {
        var admin = adminRepository.findById(id).orElseThrow(AdminNotFoundException::new);
        var updatedAdmin = adminMapper.partialUpdate(userUpdateDto, admin);
        if (userUpdateDto.getPicture() != null && !userUpdateDto.getPicture().isEmpty()) {
            String fileName = fileStorageService.storeFile(userUpdateDto.getPicture());
            updatedAdmin.setAvatarUrl(fileName);
        }
        return adminRepository.save(updatedAdmin);
    }

    /**
     * Deletes an {@link Admin} entity by its ID.
     *
     * @param id the ID of the admin to be deleted
     * @throws AdminNotFoundException if the admin with the specified ID is not found
     */
    public void deleteAdmin(Long id) {
        var admin = adminRepository.findById(id).orElseThrow(AdminNotFoundException::new);
        adminRepository.delete(admin);
    }
}
