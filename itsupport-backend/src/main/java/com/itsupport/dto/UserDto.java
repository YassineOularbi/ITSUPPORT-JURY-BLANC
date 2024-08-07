package com.itsupport.dto;

import com.itsupport.enums.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String fullName;
    private String mail;
    private String username;
    private Role role;
}
