package br.com.thalesnishida.user.service.update;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.thalesnishida.user.service.domain.user.UserGateway;
import br.com.thalesnishida.user.service.domain.user.UserId;
import br.com.thalesnishida.user.service.domain.exceptions.DomainException;
import br.com.thalesnishida.user.service.domain.user.User;
import br.com.thalesnishida.user.service.application.user.update.UpdateUserCommand;
import br.com.thalesnishida.user.service.application.user.update.DefaultUpdateUserUseCase;
import java.util.Objects;
import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateUserUseCaseTest {
    @InjectMocks
    private DefaultUpdateUserUseCase useCase;

    @Mock
    private UserGateway userGateway;

    @Test 
    public void givenAValidParams_whenCallUpdateUserCommand_thenShouldReturnUserUpdated() {
        final var aUser = User.newUser("Test", "testing@test.com", "123@0ksK");

        final var expectedName = "Tests";
        final var expectedEmail = "teste@tets.com";
        final var expectedPassword = "asd@!kKjoP1";

        final var expectedId = aUser.getId();
        final var aUpdatedAt = aUser.getUpdatedAt();

        final var aCommand = UpdateUserCommand.with(
                expectedId.getValue(),
                expectedName,
                expectedEmail,
                expectedPassword
            );
        
        Mockito.when(userGateway.findById(Mockito.eq(expectedId)))
                .thenReturn(Optional.of(aUser.clone()));

        Mockito.when(userGateway.update(any()))
                .thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(userGateway, times(1)).findById(eq(expectedId));

        Mockito.verify(userGateway, times(1)).update(argThat(
                    aUpdatedUser ->
                        Objects.equals(expectedName, aUpdatedUser.getName())
                            && Objects.equals(expectedId, aUpdatedUser.getId())
                            && Objects.equals(expectedEmail, aUpdatedUser.getEmail())
                            && Objects.equals(expectedPassword, aUpdatedUser.getPassword())
                            && aUser.getUpdatedAt().isBefore(aUpdatedUser.getUpdatedAt())                    
        ));
    }

    @Test
    public void givenAInvalidParamName_whenCallUpdateUserCommand_thenShouldReturnDomainException() {
        final var aUser = User.newUser("Test", "test@tttt.com", "test1234$$F");

        final String expectedName = null;
        final var expectedEmail = "teste@tete.com";
        final var expectedPassword = "tesT13@mofa";
        final var expectedErrorMessage = "'name' should be not null";
        final var expectedErrorCount = 1;
        
        final var expectedId = aUser.getId();

        final var aCommand = UpdateUserCommand.with(
                expectedId.getValue(),
                expectedName,
                expectedEmail,
                expectedPassword
        );

        Mockito.when(userGateway.findById(Mockito.eq(expectedId)))
                .thenReturn(Optional.of(aUser.clone()));

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
    
        Mockito.verify(userGateway, times(0)).update(any());
    }
    
    @Test
    public void givenAValidParam_whenGatewayThrowRandomException_thenShouldReturnAException(){
        final var aUser = User.newUser("tests", "test@tesr.com", "12@asdAF$");

        final var expectedName = "Test3";
        final var expectedEmail = "test@test.com";
        final var expectedPassword = "tesGT@fsfs924";
        final var expectedErrorMessage = "Gateway Error";
        final var expectedErrorCount = 1;
        final var expectedId = aUser.getId();

        final var aCommand = UpdateUserCommand.with(expectedId.getValue() ,expectedName, expectedEmail, expectedPassword);

        when(userGateway.findById(eq(expectedId))).thenReturn(Optional.of(aUser.clone()));
        when(userGateway.update(any())).thenThrow(new IllegalStateException(expectedErrorMessage));

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
    }

    @Test
    public void givenAInvalidID_whenCallUpdateUserCommand_thenShouldReturnNotFoundException() {
        final var expectedName = "Test";
        final var expectedEmail = "test@tes.com";
        final var expectedPassword = "tes@gd)*989";
        final var expectedId = "123";
        final var expectedErrorMessage = "'User' with id 123 not found";
        final var expectedErrorCount = 1;

        final var aCommand = UpdateUserCommand.with(
                expectedId,
                expectedName,
                expectedEmail,
                expectedPassword
        );

        when(userGateway.findById(eq(UserId.from(expectedId))))
            .thenReturn(Optional.empty());

        final var actualException = 
                Assertions.assertThrows(DomainException.class, () -> useCase.execute(aCommand));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }
}
