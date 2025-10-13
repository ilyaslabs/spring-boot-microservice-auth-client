package io.github.ilyaslabs.microservice.authclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Represents a request for refreshing an expired or expiring access token.
 *
 * This class includes the refresh token required to generate a new access token.
 * It leverages Lombok annotations to automatically generate boilerplate code,
 * including getters, setters, constructors, and fluent-style accessors.
 *
 * The class is designed to be mutable and can be instantiated with or
 * without providing initial values for its fields.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Accessors(chain = true)
class RefreshTokenRequest {

    private String refreshToken;
}
