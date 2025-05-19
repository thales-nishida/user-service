package br.com.thalesnishida.user.service.create;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.thalesnishida.user.service.application.user.create.CreateUserCommand;
import br.com.thalesnishida.user.service.application.user.create.DefaultCreateUserUseCase;
import br.com.thalesnishida.user.service.domain.user.UserGateway;
import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseTest {

    @InjectMocks
    private DefaultCreateUserUseCase useCase;

    @Mock
    private UserGateway userGateway;

    @Test
    public void givenAValidParams_whenCallCreateUserCommand_thenShouldCreateUser() {
        final var expectedName = "Teste";
        final var expectedEmail = "teste@test.com";
        final var expectedPassword = "12C98ssf$";

        final var aCommand = CreateUserCommand.with(expectedName, expectedEmail, expectedPassword);

        when(userGateway.create(any()))
                .thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(userGateway, times(1)).create(argThat(aUser ->
                Objects.equals(expectedName, aUser.getName())
                    && Objects.equals(expectedEmail, aUser.getEmail())
                    && Objects.equals(expectedPassword, aUser.getPassword())
                    && Objects.nonNull(aUser.getId())
                    && Objects.nonNull(aUser.getCreatedAt())
                    && Objects.nonNull(aUser.getUpdatedAt())
        ));
   }
   
   @Test
   public void givenAInvalidName_whenCallCreateUserCommand_thenShouldReturnADomainException() {
       final String expectedName = null;
       final var expectedEmail = "teste@teste.com";
       final var expectedPassword  = "12@sfFE4";
       final var expectedErrorMessage = "'name' should be not null";
       final var expectedErrorCount = 1;

       final var aCommand = CreateUserCommand.with(expectedName, expectedEmail, expectedPassword);

       final var notification = useCase.execute(aCommand).getLeft();

       Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());
       Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());

       Mockito.verify(userGateway, times(0)).create(any());
   }

   @Test
   public void givenAInvalidCommand_whenGatewayThrowsRandomException_thenShouldReturnaADomainException() {
       final var expectedName = "Test";
       final var expectedEmail = "test@teste.com";
       final var expectedPassword = "12#lms#9JJ";
       final var expectedErrorMessage = "Gateway Error";
       final var expectedErrorCount = 1;

       final var aCommand = CreateUserCommand.with(expectedName, expectedEmail, expectedPassword);

       when(userGateway.create(any())).thenThrow(new IllegalStateException(expectedErrorMessage));

       final var notification = useCase.execute(aCommand).getLeft();

       Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());
       Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
   }
}
