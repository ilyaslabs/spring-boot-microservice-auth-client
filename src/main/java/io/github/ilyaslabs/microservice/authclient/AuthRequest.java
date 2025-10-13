package io.github.ilyaslabs.microservice.authclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Represents an authentication request containing the credentials
 * necessary to authenticate a user.
 *
 * This class includes properties for the username and password, which are
 * required for the authentication process. It leverages Lombok annotations
 * to reduce boilerplate code, such as getters, setters, constructors, and
 * fluent-style accessors.
 *
 * The class is designed to be mutable and can be instantiated with or
 * without providing initial values for its fields.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Accessors(chain = true)
class AuthRequest {

    private String username;
    private String password;
}
