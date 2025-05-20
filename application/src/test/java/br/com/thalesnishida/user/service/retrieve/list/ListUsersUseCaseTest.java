package br.com.thalesnishida.user.service.retrieve.list;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.reset;

import br.com.thalesnishida.user.service.domain.user.UserGateway;

@ExtendWith(MockitoExtension.class)
public class ListUserUseCaseTest {
    @InjectMocks
    private DefaultListUsersUseCase useCase;

    @Mock
    private UserGateway userGateway;

    @BeforeEach
    void cleanUp(){
        reset(userGateway);
    }
    
    @Test
    public void givenAValidQuery_whenCallsListUsers_thenShouldReturnAListUsers() {
    }

    @Test
    public void givenAValidQuery_whenCallsListUsers_thenShouldReturnAEmptyList() {
    }

    @Test
    public void givenAValidQuery_whenGatewayThrowsException_shouldReturnException() {
    }
}
