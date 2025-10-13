package io.github.ilyaslabs.microservice.authclient;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Represents the response received after an authentication request.
 * Contains details such as access token, refresh token, token expiration,
 * and user-related information.
 */
@Data
@Accessors(chain = true)
class AuthResponse {

    private String token;
    private String refreshToken;
    private List<String> scopes;
    private String userId;
    private String username;

    /**
     * expiration time in seconds
     */
    private Long expiresIn;

    private Long refreshTokenExpiresIn;
}
