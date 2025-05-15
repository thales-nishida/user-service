package br.com.thalesnishida.user.service.application.user.create;

import br.com.thalesnishida.user.service.application.UseCase;
import br.com.thalesnishida.user.service.domain.validations.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateUserUseCase extends UseCase<CreateUserCommand, Either<Notification, CreateUserOutput>> {}
