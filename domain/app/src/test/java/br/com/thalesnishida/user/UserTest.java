package br.com.thalesnishida.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import br.com.thalesnishida.exceptions.DomainException;
import br.com.thalesnishida.validations.handler.ThrowValidationHandler;

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
        Assertions.assertNotNull(actualUser.getCreatedAt());
        Assertions.assertNotNull(actualUser.getUpdatedAt());
    }

    @Test
    public void givenAInvalidParamsNullName_whenCallNewUser_thenShouldReturnADomainException() {
        final String expectedName = null;
        final var expectedEmail = "test@test.com";
        final var expectedPassword = "test123";
        final var expectedErrorMessage = "'name' should be not null";
        final var expectedErrorCount = 1;

        final var actualUser = User.newUser(expectedName, expectedEmail, expectedPassword);

        final var actualException = 
            Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAInvalidParamsEmptyName_whenCallNewUser_thenShouldReturnADomainException() {
        final var expectedName = "";
        final var expectedEmail = "test@test.com";
        final var expectedPassword = "test123";
        final var expectedErrorMessage = "'name' should be not empty";
        final var expectedErrorCount = 1;

        final var actualUser = User.newUser(expectedName, expectedEmail, expectedPassword);

        final var actualException = 
            Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAInvalidParamNameLess2Length_whenCallNewUser_thenShouldReturnADomainException() {
        final var expectedName = "T";
        final var expectedEmail = "test@test.com";
        final var expectedPassword = "test123";
        final var expectedErrorMessage = "'name' should be between 2 and 255 characters";
        final var expectedErrorCount = 1;

        final var actualUser = User.newUser(expectedName, expectedEmail, expectedPassword);

        final var actualException = 
            Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAInvalidParamNameMore255Length_whenCallNewUser_thenShouldReturnADomainException() {
        final var expectedName = """
                cwofydnaikxhzsywekxymfynlesjscwywrjrrpgihitqnogekesncqzyytulcgtjqziilhfqfiucjmyfepnxwojgkubntddaobfiqmka
                qfkafukodromrunqpsjvxlkzwgsdmqyrpfvfjctcxmjcheokrfbydbsoolqnavovhuveoujiuddgqaakbtwxxpkefwbyfasgypbwdoffyx
                kulvdvympkeqblfocvhrlpzpxfbbdrkbkegbmhcimwxmyp
            """;
        final var expectedEmail = "test@test.com";
        final var expectedPassword = "test123";
        final var expectedErrorMessage = "'name' should be between 2 and 255 characters";
        final var expectedErrorCount = 1;

        final var actualUser = User.newUser(expectedName, expectedEmail, expectedPassword);

        final var actualException = 
            Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }
    
    @Test
    public void givenAInvalidParamEmailNull_whenCallNewUser_thenShouldReturnADomainException() {
        final var expectedName = "Teste";
        final String expectedEmail = null;
        final var expectedPassword = "test123";
        final var expectedErrorMessage = "'email' should be not null";
        final var expectedErrorCount = 1;

        final var actualUser = User.newUser(expectedName, expectedEmail, expectedPassword);

        final var actualException = 
            Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAInvalidParamEmailEmpty_whenCallNewUser_thenShouldReturnADomainException() {
        final var expectedName = "Teste";
        final var expectedEmail = "";
        final var expectedPassword = "test123";
        final var expectedErrorMessage = "'email' should be not empty";
        final var expectedErrorCount = 1;

        final var actualUser = User.newUser(expectedName, expectedEmail, expectedPassword);

        final var actualException = 
            Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }
    
    @Test
    public void givenAInvalidParamEmailInvalidFormat_whenCallNewUser_thenShouldReturnADomainException() {
        final var expectedName = "Teste";
        final var expectedEmail = "testtest.com";
        final var expectedPassword = "test123";
        final var expectedErrorMessage = "'email' format invalid";
        final var expectedErrorCount = 1;

        final var actualUser = User.newUser(expectedName, expectedEmail, expectedPassword);

        final var actualException = 
            Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }
    
    @Test
    public void givenAInvalidParamPasswordNull_whenCallNewUser_thenShouldReturnADomainException() {
        final var expectedName = "Teste";
        final var expectedEmail = "teste@teste.com";
        final String expectedPassword = null;
        final var expectedErrorMessage = "'password' should be not null";
        final var expectedErrorCount = 1;

        final var actualUser = User.newUser(expectedName, expectedEmail, expectedPassword);

        final var actualException = 
            Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }
    
    @Test
    public void givenAInvalidParamPasswordLess8Length_whenCallNewUser_thenShouldReturnADomainException() {
        final var expectedName = "Teste";
        final var expectedEmail = "teste@teste.com";
        final var expectedPassword = "0";
        final var expectedErrorMessage = "'password' should be contains at least 8 characters and at most 20 characters";
        final var expectedErrorCount = 1;

        final var actualUser = User.newUser(expectedName, expectedEmail, expectedPassword);

        final var actualException = 
            Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }
    
    @Test
    public void givenAInvalidParamPasswordWithNoDigit_whenCallNewUser_thenShouldReturnADomainException() {
        final var expectedName = "Teste";
        final var expectedEmail = "teste@teste.com";
        final var expectedPassword = "nodigitpass";
        final var expectedErrorMessage = "'password' should be contains at least one digit, uppercase, lowercase, special character";
        final var expectedErrorCount = 1;

        final var actualUser = User.newUser(expectedName, expectedEmail, expectedPassword);

        final var actualException = 
            Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAInvalidParamPasswordWithoutUpperCaseAlphabet_whenCallNewUser_thenShouldReturnADomainException() {
        final var expectedName = "Teste";
        final var expectedEmail = "teste@teste.com";
        final var expectedPassword = "nodig2itpass";
        final var expectedErrorMessage = "'password' should be contains at least one digit, uppercase, lowercase, special character";
        final var expectedErrorCount = 1;

        final var actualUser = User.newUser(expectedName, expectedEmail, expectedPassword);

        final var actualException = 
            Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAInvalidParamPasswordWithoutLowerCaseAlphabet_whenCallNewUser_thenShouldReturnADomainException() {
        final var expectedName = "Teste";
        final var expectedEmail = "teste@teste.com";
        final var expectedPassword = "TEST1T@TEST";
        final var expectedErrorMessage = "'password' should be contains at least one digit, uppercase, lowercase, special character";
        final var expectedErrorCount = 1;

        final var actualUser = User.newUser(expectedName, expectedEmail, expectedPassword);

        final var actualException = 
            Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }
    
    @Test
    public void givenAInvalidParamPasswordNoIncludesSpecialCharacters_whenCallNewUser_thenShouldReturnADomainException() {
        final var expectedName = "Teste";
        final var expectedEmail = "teste@teste.com";
        final var expectedPassword = "TEST1TEST";
        final var expectedErrorMessage = "'password' should be contains at least one digit, uppercase, lowercase, special character";
        final var expectedErrorCount = 1;

        final var actualUser = User.newUser(expectedName, expectedEmail, expectedPassword);

        final var actualException = 
            Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }
    
    @Test
    public void givenAValidParams_whenCallUserUpdate_thenShouldUpdateUser() {
        final var name = "Test";
        final var email = "test@test.com.br";
        final var password = "D0&*test123";

        final var aUser = User.newUser(name, email, password);
        final var createdAt = aUser.getCreatedAt();
        final var updatedAt = aUser.getUpdatedAt();

        Assertions.assertDoesNotThrow(() -> aUser.validate(new ThrowValidationHandler()));

        final var expectedName = "Test Update";
        final var expectedEmail = "test@update.com.br";
        final var expectedPassword = "D&*3e@upPd3";

        final var actualUser = aUser.update(expectedName, expectedEmail, expectedPassword);

        Assertions.assertDoesNotThrow(() -> actualUser.validate(new ThrowValidationHandler()));

        Assertions.assertNotNull(actualUser);
        Assertions.assertEquals(aUser.getId(), actualUser.getId());
        Assertions.assertEquals(expectedName, actualUser.getName());
        Assertions.assertEquals(expectedEmail, actualUser.getEmail());
        Assertions.assertEquals(expectedPassword, actualUser.getPassword());
        Assertions.assertEquals(createdAt, actualUser.getCreatedAt());
        Assertions.assertTrue(actualUser.getUpdatedAt().isAfter(updatedAt));
    }
}
