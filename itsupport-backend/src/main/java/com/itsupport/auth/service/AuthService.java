package com.itsupport.auth.service;

import com.itsupport.auth.model.*;
import com.itsupport.mapper.*;
import com.itsupport.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public AuthResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return AuthResponse.builder().token(jwtService.generateToken(userMapper.toDto(userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Invalid user request..!!"))))).build();
        } else {
            throw new UsernameNotFoundException("Invalid user request..!!");
        }
    }

    public AuthResponse adminRegister(RegisterRequest registerRequest){
        if(userRepository.findByUsername(registerRequest.getUsername()).isEmpty()){
            var admin = adminMapper.toEntity(registerRequest);
            admin.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            return AuthResponse.builder().token(jwtService.generateToken(adminMapper.toDto(adminRepository.save(admin)))).build();
        } else {
            throw new UsernameNotFoundException("Invalid user request..!!");
        }
    }

    public AuthResponse technicianRegister(RegisterRequest registerRequest){
        if(userRepository.findByUsername(registerRequest.getUsername()).isEmpty()){
            var technician = technicianMapper.toEntity(registerRequest);
            technician.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            return AuthResponse.builder().token(jwtService.generateToken(technicianMapper.toDto(technicianRepository.save(technician)))).build();
        } else {
            throw new UsernameNotFoundException("Invalid user request..!!");
        }
    }

    public AuthResponse clientRegister(RegisterRequest registerRequest){
        if(userRepository.findByUsername(registerRequest.getUsername()).isEmpty()){
            var client = clientMapper.toEntity(registerRequest);
            client.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            return AuthResponse.builder().token(jwtService.generateToken(clientMapper.toDto(clientRepository.save(client)))).build();
        } else {
            throw new UsernameNotFoundException("Invalid user request..!!");
        }
    }
}
