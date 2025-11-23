package io.github.ilyaslabs.microservice.authclient;

import io.github.ilyaslabs.microservice.test.common.FixedClockConfiguration;
import io.github.ilyaslabs.microservice.test.common.MutableClock;
import org.junit.jupiter.api.BeforeEach;
import org.mockserver.client.MockServerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.context.annotation.Import;
import org.testcontainers.mockserver.MockServerContainer;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

/**
 * BaseTest is an abstract class designed to be the foundation for all test classes
 * that require configurations for an authentication client, mock server, and mutable clock.
 * It leverages Spring's testing framework along with specific configurations for test setup.
 * Provides utility methods and shared setup logic to simplify and standardize test configurations.
 *
 * Annotations:
 * - @SpringBootTest: Configures the integration test and loads the Spring application context.
 * - @ImportTestcontainers: Automatically starts Testcontainers dependencies during tests.
 * - @Import: Includes additional configurations for the test context, such as a fixed clock setup.
 *
 * Fields:
 * - authClientConfig: Autowired configuration for managing authentication client properties.
 * - mockServerClient: Autowired client used for interacting with a mocked server.
 * - mockServerContainer: Autowired container for running the mock server.
 * - clock: Autowired mutable clock instance, often used for time manipulation during testing.
 * - objectMapper: A pre-configured Jackson ObjectMapper instance for JSON serialization and deserialization.
 *
 * Methods:
 * - setUp: A @BeforeEach method that sets up the authentication client configurations before each test.
 *   It ensures the token and refresh token URLs are correctly initialized using the mock server container.
 * - getAuthTokenUrl: Retrieves the authorization token URL based on the mock server's endpoint.
 * - getRefreshTokenUrl: Retrieves the refresh token URL based on the mock server's endpoint.
 * - toJsonString: Converts a Java object into its JSON string representation using the ObjectMapper.
 *   It throws a RuntimeException in case of serialization errors.
 */
@SpringBootTest(classes = { AutoConfig.class })
@ImportTestcontainers(TestContainersConfiguration.class)
@Import(FixedClockConfiguration.class)
abstract class BaseTest {

    @Autowired
    protected AuthClientConfig authClientConfig;

    @Autowired
    protected MockServerClient mockServerClient;

    @Autowired
    protected MockServerContainer mockServerContainer;

    @Autowired
    MutableClock clock;

    protected ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        authClientConfig.setTokenUrl(getAuthTokenUrl());
        authClientConfig.setRefreshTokenUrl(getRefreshTokenUrl());
    }

    protected String getAuthTokenUrl() {
        return mockServerContainer.getEndpoint() + "/auth/token";
    }

    protected String getRefreshTokenUrl() {
        return mockServerContainer.getEndpoint() + "/auth/refresh-token";
    }

    /**
     * Convert object to json string
     *
     * @param obj object
     * @return json string
     */
    protected String toJsonString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
