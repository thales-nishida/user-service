package br.com.thalesnishida.user.service.application.user.retrieve.get;

import br.com.thalesnishida.user.service.domain.user.UserGateway;
import br.com.thalesnishida.user.service.domain.exceptions.DomainException;
import br.com.thalesnishida.user.service.domain.validations.Error;
import br.com.thalesnishida.user.service.domain.user.UserId;
import java.util.Objects;
import java.util.Optional;

public class DefaultGetUserByIdUseCase extends GetUserByIdUseCase {

    private final UserGateway userGateway;

    public DefaultGetUserByIdUseCase(final UserGateway userGateway){
        this.userGateway = Objects.requireNonNull(userGateway);
    }

    @Override
    public UserOutput execute(final String anIn) {
        final var anUserId = UserId.from(anIn);

        return this.userGateway.findById(anUserId)
            .map(UserOutput::from)
            .orElseThrow(() -> DomainException.with(new Error("'user' with id %s was not found".formatted(anUserId.getValue()))));
    }
}


