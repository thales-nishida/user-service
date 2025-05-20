package br.com.thalesnishida.user.service.application.delete;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import br.com.thalesnishida.user.service.application.user.delete.DefaultDeleteUserUseCase;
import br.com.thalesnishida.user.service.domain.user.UserGateway;
import br.com.thalesnishida.user.service.domain.user.User;
import br.com.thalesnishida.user.service.domain.user.UserId;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;


@ExtendWith(MockitoExtension.class)
public class DeleteUserUseCaseTest {

    @InjectMocks
    private DefaultDeleteUserUseCase useCase;

    @Mock
    private UserGateway userGateway;
    
    @BeforeEach
    void cleanUp() {
        reset(userGateway);
    }

    @Test
    public void ginvenAValidId_whenCallsDeleteUser_thenShouldOK(){
        final var aUser = User.newUser("test", "test@test.com", "tes@4242sdJJ");
        final var expectedId = aUser.getId();

        doNothing()
            .when(userGateway).deleteById(eq(expectedId));

        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        verify(userGateway, times(1)).deleteById(eq(expectedId));
    }

    @Test
    public void givenAInvalidId_whenCallsDeleteUser_thenShouldOK() {
        final var expectedId = UserId.from("123");

        doNothing()
            .when(userGateway).deleteById(eq(expectedId));

        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        verify(userGateway, times(1)).deleteById(eq(expectedId));
    }

    @Test
    public void givenAValidId_whenGetawayThrowsException_shouldReturnException() {
        final var aUser = User.newUser("test", "test@test.com", "tes@tT232#");
        final var expectedId = aUser.getId();

        doThrow(new IllegalStateException("Gateway error"))
            .when(userGateway).deleteById(eq(expectedId));

        Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(expectedId.getValue()));

        verify(userGateway, times(1)).deleteById(eq(expectedId));
    }
}

