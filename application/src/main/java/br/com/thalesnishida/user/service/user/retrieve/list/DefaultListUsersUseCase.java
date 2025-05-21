package br.com.thalesnishida.user.service.application.user.retrieve.list;

import br.com.thalesnishida.user.service.domain.user.UserSearchQuery;
import br.com.thalesnishida.user.service.domain.user.UserGateway;
import br.com.thalesnishida.user.service.domain.pagination.Pagination;
import java.util.Objects;

public class DefaultListUsersUseCase extends ListUserUseCase {

    private final UserGateway userGateway;

    public DefaultListUsersUseCase(final UserGateway userGateway) {
        this.userGateway = Objects.requireNonNull(userGateway);
    }

    @Override
    public Pagination<UserListOutput> execute(final UserSearchQuery aQuery) {
        return this.userGateway.findAll(aQuery)
                .map(UserListOutput::from);
    }
}


