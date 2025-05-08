package br.com.thalesnishida.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class UserTest {

    @Test
    public void givenAValidParams_whenCallUser_thenShouldInstantiateUser() {
        final var expectedName = "Test";
        final var expectedEmail = "test@test.com.br";
        final var expectedPassword = "test123";

        final var actualUser = User.newUser(expectedName, expectedEmail, expectedPassword);

        Assertions.assertNotNull(actualUser);
        Assertions.assertNotNull(actualUser.getId());
        Assertions.assertEquals(expectedName, actualUser.getName());
        Assertions.assertEquals(expectedEmail, actualUser.getEmail());
        Assertions.assertEquals(expectedPassword, actualUser.getPassword());
        
    }
}
