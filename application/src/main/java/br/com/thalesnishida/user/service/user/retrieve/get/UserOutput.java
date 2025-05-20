package br.com.thalesnishida.user.service.application.user.retrieve.get;

import br.com.thalesnishida.user.service.domain.user.UserId;
import br.com.thalesnishida.user.service.domain.user.User;

import java.time.Instant;

public record UserOutput(
    UserId id,
    String name,
    String email,
    String password,
    Instant createdAt,
    Instant updatedAt
) {
    public static UserOutput from(final User aUser) {
        return new UserOutput(
            aUser.getId(),
            aUser.getName(),
            aUser.getEmail(),
            aUser.getPassword(),
            aUser.getCreatedAt(),
            aUser.getUpdatedAt()
        );
    }

}

