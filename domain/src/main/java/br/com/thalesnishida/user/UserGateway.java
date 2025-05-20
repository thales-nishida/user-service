package br.com.thalesnishida.user.service.domain.user;

import br.com.thalesnishida.user.service.domain.pagination.Pagination;
import java.util.Optional;

public interface UserGateway {
    User create(User aUser);
    void deleteById(UserId anId);
    Optional<User> findById(UserId anId);
    User update(User aUser);
    Pagination<User> findAll(UserSearchQuery aQuery);
}
