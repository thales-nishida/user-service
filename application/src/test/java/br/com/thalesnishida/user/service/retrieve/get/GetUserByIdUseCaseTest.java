package br.com.thalesnishida.user.service.retrieve.get;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.thalesnishida.user.service.domain.user.UserGateway;
import br.com.thalesnishida.user.service.domain.exceptions.DomainException;
import br.com.thalesnishida.user.service.domain.user.User;
import br.com.thalesnishida.user.service.domain.user.UserId;
import br.com.thalesnishida.user.service.application.user.retrieve.get.DefaultGetUserByIdUseCase;
import java.util.Optional;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.eq;

@ExtendWith(MockitoExtension.class)
public class GetUserByIdUseCaseTest {

    @InjectMocks
    private DefaultGetUserByIdUseCase useCase;

    @Mock
    private UserGateway userGateway;

    @BeforeEach
    void cleanUp() {
        reset(userGateway);
    }

    @Test
    public void givenAValidaId_whenCallGetUserById_thenShouldReturnUser() {
        final var expectedName = "Test";
        final var expectedEmail = "test@tes.com";
        final var expectedPassword = "123MKMddd!@";
        final var aUser = User.newUser(expectedName, expectedEmail, expectedPassword);
        final var expectedId = aUser.getId();
        
        when(userGateway.findById(eq(expectedId)))
            .thenReturn(Optional.of(aUser.clone()));

        final var actualUser = useCase.execute(expectedId.getValue());

        Assertions.assertEquals(expectedId, actualUser.id());
        Assertions.assertEquals(expectedName, actualUser.name());
        Assertions.assertEquals(expectedEmail, actualUser.email());
        Assertions.assertEquals(expectedPassword, actualUser.password());
        Assertions.assertEquals(aUser.getCreatedAt(), actualUser.createdAt());
        Assertions.assertEquals(aUser.getUpdatedAt(), actualUser.updatedAt());
    }

    @Test
    public void givenAInvalidId_whenCallGetUser_thenShouldReturnNotFound() {
        final var expectedId = UserId.from("123");
        final var expectedErrorMessage = "'user' with id 123 was not found";

        when(userGateway.findById(eq(expectedId)))
            .thenReturn(Optional.empty());
        
        final var actualException = 
            Assertions.assertThrows(DomainException.class, () -> useCase.execute(expectedId.getValue()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
    public void givenAValidId_whenGatewayThrowsException_shouldReturnException() {
        final var expectedId = UserId.from("123");
        final var expectedErrorMessage = "Gateway error";

        when(userGateway.findById(eq(expectedId)))
            .thenThrow(new IllegalStateException(expectedErrorMessage));

        final var actualException = 
            Assertions.assertThrows(IllegalStateException.class,() -> useCase.execute(expectedId.getValue()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }

} 
