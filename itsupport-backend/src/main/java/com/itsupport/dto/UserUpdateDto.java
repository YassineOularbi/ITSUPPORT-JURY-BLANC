package com.itsupport.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {
    private String fullName;
    private String mail;
    private String username;
}
