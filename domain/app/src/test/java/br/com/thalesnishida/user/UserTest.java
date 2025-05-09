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

    @Test
    public void givenAInvalidParamsNullName_whenCallNewUser_thenShouldReturnADomainException() {
        final String expectedName = null;
        final var expectedEmail = "test@test.com";
        final var expecedtPassword = "test123";
        final var expecedErrorMessage = "'name' should be not null";

        final var actualUser = User.newUser(expectedName, expecedtEmail, expectedPassword);

        final var actualException = 
            Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getError().get(0).message());
    }
}
