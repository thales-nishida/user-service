package br.com.thalesnishida.user.service.retrieve.list;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.eq;
import br.com.thalesnishida.user.service.domain.user.User;
import br.com.thalesnishida.user.service.domain.user.UserGateway;
import br.com.thalesnishida.user.service.domain.user.UserSearchQuery;
import br.com.thalesnishida.user.service.domain.pagination.Pagination;
import br.com.thalesnishida.user.service.application.user.retrieve.list.DefaultListUsersUseCase;
import br.com.thalesnishida.user.service.application.user.retrieve.list.UserListOutput;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ListUsersUseCaseTest {
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
        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "";
        final var expectedSort = "createdAt";
        final var expectedDirection = "asc";

        final var aQuery = new UserSearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        final var users = List.of(
                User.newUser("test", "test@test.com", "12jkKO&%"),
                User.newUser("test1", "test@test1.com", "1s2jkKO&%"),
                User.newUser("test2", "test@test2.com", "12a2jkKO&%"),
                User.newUser("test3", "test@test3.com", "123kjkKO&%"),
                User.newUser("test4", "test@test4.com", "123jkKO&%")
        );

        final var expectedPagination = new Pagination<>(expectedPage, expectedPerPage, users.size(), users);
        final var expectedItemsCount = 5;
        final var expectedResults = expectedPagination.map(UserListOutput::from);

        when(userGateway.findAll(eq(aQuery)))
            .thenReturn(expectedPagination);

        final var actualResult = useCase.execute(aQuery);

        Assertions.assertEquals(expectedItemsCount, actualResult.items().size());
        Assertions.assertEquals(expectedResults, actualResult);
        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(users.size(), actualResult.total());
    }

    @Test
    public void givenAValidQuery_whenCallsListUsers_thenShouldReturnAEmptyList() {
        final var users = List.<User>of();

        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "";
        final var expectedSort = "createdAt";
        final var expectedDirection = "asc";
                
        final var aQuery = new UserSearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        final var expectedPagination =
            new Pagination<>(expectedPage, expectedPerPage, users.size(), users);

        final var expectedItemsCount = 0;
        final var expectedResult = expectedPagination.map(UserListOutput::from);

        when(userGateway.findAll(eq(aQuery)))
            .thenReturn(expectedPagination);

        final var actualResult = useCase.execute(aQuery);

        Assertions.assertEquals(expectedItemsCount, actualResult.items().size());
        Assertions.assertEquals(expectedResult, actualResult);
        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(users.size(), actualResult.total());
    }

    @Test
    public void givenAValidQuery_whenGatewayThrowsException_shouldReturnException() {
        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "";
        final var expectedSort = "createdAt";
        final var expectedDirection = "asc";
        final var expectedErrorMessage = "Gateway error";

        final var aQuery = new UserSearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        when(userGateway.findAll(eq(aQuery)))
            .thenThrow(new IllegalStateException(expectedErrorMessage));

        final var actualException = Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(aQuery)); 

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}
