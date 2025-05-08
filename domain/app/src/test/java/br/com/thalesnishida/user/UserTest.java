package br.com.thalesnishida.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class UserTest {

    @Test
    public void givenAValidParams_whenCallUser_thenShouldInstantiateUser() {
        final var name = "Test";
        final var email = "test@test.com.br";
        final var password = "test123";

        final var actualUser = User.newUser(name, email, password);

        Assertions.assertNotNull(actualUser);
        
    }
}
