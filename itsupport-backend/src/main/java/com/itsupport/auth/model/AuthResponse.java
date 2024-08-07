package com.itsupport.auth.model;

import lombok.*;

/**
 * Represents the authentication response containing the JWT token.
 *
 * This class is used to encapsulate the JWT token returned after a successful
 * authentication. It includes the token as a string which can be used for
 * subsequent authorization requests.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    /**
     * The JWT token issued after successful authentication.
     */
    private String token;
}
