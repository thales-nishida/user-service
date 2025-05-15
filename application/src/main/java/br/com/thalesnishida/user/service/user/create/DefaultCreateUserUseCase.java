package br.com.thalesnishida.user.service.application.user.create;

import br.com.thalesnishida.user.service.domain.user.User;
import br.com.thalesnishida.user.service.domain.user.UserGateway;
import br.com.thalesnishida.user.service.domain.validations.handler.Notification;
import io.vavr.control.Either;
import java.util.Objects;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class DefaultCreateUserUseCase extends CreateUserUseCase {

    private UserGateway userGateway;

    public DefaultCreateUserUseCase(final UserGateway userGateway) {
        this.userGateway = Objects.requireNonNull(userGateway);
    }
    
    @Override
    public Either<Notification, CreateUserOutput> execute(final CreateUserCommand aCommand) {
        final var aName = aCommand.name();
        final var aEmail = aCommand.email();
        final var aPassword = aCommand.password();

        final var notification = Notification.create();

        final var aUser = User.newUser(aName, aEmail, aPassword);
        aUser.validate(notification);

        return notification.hasError() ? Left(notification) : create(aUser);
    }

    private Either<Notification, CreateUserOutput> create(User aUser) {
        return Try(() -> this.userGateway.create(aUser))
                .toEither()
                .bimap(Notification::create, CreateUserOutput::from);
    }
}

