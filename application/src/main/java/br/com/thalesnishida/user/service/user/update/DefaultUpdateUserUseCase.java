package br.com.thalesnishida.user.service.application.user.update;

import br.com.thalesnishida.user.service.domain.validations.handler.Notification;
import br.com.thalesnishida.user.service.domain.exceptions.DomainException;
import br.com.thalesnishida.user.service.domain.validations.handler.Notification;
import br.com.thalesnishida.user.service.domain.validations.Error;
import br.com.thalesnishida.user.service.domain.user.UserGateway;
import br.com.thalesnishida.user.service.domain.user.UserId;
import br.com.thalesnishida.user.service.domain.user.User;
import io.vavr.control.Either;
import java.util.Objects;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class DefaultUpdateUserUseCase extends UpdateUserUseCase {

    private final UserGateway userGateway;

    public DefaultUpdateUserUseCase (final UserGateway userGateway) {
        this.userGateway = Objects.requireNonNull(userGateway);
    }

    @Override
    public Either<Notification, UpdateUserOutput> execute(final UpdateUserCommand aCommand) {
        final var anId = UserId.from(aCommand.id());
        final var aName = aCommand.name();
        final var aEmail = aCommand.email();
        final var aPassword = aCommand.password();

        final var aUser = this.userGateway.findById(anId)
            .orElseThrow(() -> DomainException.with(new Error("'User' with id %s not found".formatted(anId.getValue()))));
        
        final var notification = Notification.create();
        
        aUser
            .update(aName, aEmail, aPassword)
            .validate(notification);
       
        return notification.hasError() ? Left(notification) : update(aUser);
    }

    private Either<Notification, UpdateUserOutput> update(final User aUser) {
        return Try(() -> this.userGateway.update(aUser))
            .toEither()
            .bimap(Notification::create, UpdateUserOutput::from);
    }
}

