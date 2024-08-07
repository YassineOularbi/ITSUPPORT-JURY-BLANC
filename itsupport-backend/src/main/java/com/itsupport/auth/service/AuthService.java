package com.itsupport.auth.service;

import com.itsupport.auth.exception.*;
import com.itsupport.auth.model.*;
import com.itsupport.mapper.*;
import com.itsupport.model.User;
import com.itsupport.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for managing authentication and user registration.
 *
 * This service handles user login and registration for different roles (admin, technician, client).
 * It also interacts with the database repositories and manages JWT token generation.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;
    private final TechnicianRepository technicianRepository;
    private final UserMapper userMapper;
    private final AdminMapper adminMapper;
    private final ClientMapper clientMapper;
    private final TechnicianMapper technicianMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    /**
     * Authenticates a user and generates a JWT token.
     *
     * @param loginRequest the login request containing username and password
     * @return an {@link AuthResponse} containing the generated JWT token
     * @throws LoginException if authentication fails due to invalid credentials, user not found, or other issues
     */
    public AuthResponse login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            return AuthResponse.builder()
                    .token(jwtService.generateToken(userMapper.toDto((User) authentication.getPrincipal())))
                    .build();
        } catch (BadCredentialsException e) {
            throw LoginException.invalidCredentials();
        } catch (UsernameNotFoundException e) {
            throw LoginException.userNotFound();
        } catch (Exception e) {
            throw LoginException.authenticationFailed();
        }
    }

    /**
     * Registers a new admin and generates a JWT token.
     *
     * @param registerRequest the registration request containing user details
     * @return an {@link AuthResponse} containing the generated JWT token
     * @throws RegistrationException if a user with the same username already exists
     */
    public AuthResponse adminRegister(RegisterRequest registerRequest) {
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new RegistrationException();
        }
        var admin = adminMapper.toEntity(registerRequest);
        admin.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        return AuthResponse.builder()
                .token(jwtService.generateToken(adminMapper.toDto(adminRepository.save(admin))))
                .build();
    }

    /**
     * Registers a new technician and generates a JWT token.
     *
     * @param registerRequest the registration request containing user details
     * @return an {@link AuthResponse} containing the generated JWT token
     * @throws RegistrationException if a user with the same username already exists
     */
    public AuthResponse technicianRegister(RegisterRequest registerRequest) {
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new RegistrationException();
        }
        var technician = technicianMapper.toEntity(registerRequest);
        technician.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        technician.setAvailability(true);
        return AuthResponse.builder()
                .token(jwtService.generateToken(technicianMapper.toDto(technicianRepository.save(technician))))
                .build();
    }

    /**
     * Registers a new client and generates a JWT token.
     *
     * @param registerRequest the registration request containing user details
     * @return an {@link AuthResponse} containing the generated JWT token
     * @throws RegistrationException if a user with the same username already exists
     */
    public AuthResponse clientRegister(RegisterRequest registerRequest) {
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new RegistrationException();
        }
        var client = clientMapper.toEntity(registerRequest);
        client.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        return AuthResponse.builder()
                .token(jwtService.generateToken(clientMapper.toDto(clientRepository.save(client))))
                .build();
    }
}
