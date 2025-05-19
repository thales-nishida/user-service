package br.com.thalesnishida.user.service.update;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.thalesnishida.user.service.domain.user.UserGateway;
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
                .thenReturn(Optional.of(aUser));

        Mockito.when(userGateway.update(any()))
                .thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(userGateway, times(1)).findById(eq(expectedId));

        Mockito.verify(userGateway, times(1)).update(argThat(
                    aUpdatedUser ->
                        Objects.equals(expectedName, aUpdatedUser.getName())
                            && Objects.equals(expectedEmail, aUpdatedUser.getEmail())
                            && Objects.equals(expectedPassword, aUpdatedUser.getPassword())
                            && Objects.equals(expectedId, aUpdatedUser.getId())
                            && aUser.getUpdatedAt().isBefore(aUpdatedUser.getUpdatedAt())                    
        ));
    }
}
