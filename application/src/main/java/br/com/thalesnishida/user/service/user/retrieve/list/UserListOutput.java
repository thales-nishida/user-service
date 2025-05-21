package br.com.thalesnishida.user.service.application.user.retrieve.list;

import br.com.thalesnishida.user.service.domain.user.UserId;
import br.com.thalesnishida.user.service.domain.user.User;
import java.time.Instant;

public record UserListOutput(
    UserId id,
    String name,
    String email,
    String password,
    Instant createdAt,
    Instant updatedAt
) {
    public static UserListOutput from(final User aUser) {
        return new UserListOutput(
                aUser.getId(),
                aUser.getName(),
                aUser.getEmail(),
                aUser.getPassword(),
                aUser.getCreatedAt(),
                aUser.getUpdatedAt()
        );
    }
}
