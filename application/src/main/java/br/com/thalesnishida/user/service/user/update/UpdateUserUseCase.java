package br.com.thalesnishida.user.service.application.user.update;

import br.com.thalesnishida.user.service.domain.validations.handler.Notification;
import br.com.thalesnishida.user.service.application.UseCase;
import io.vavr.control.Either;

public abstract class UpdateUserUseCase extends UseCase<UpdateUserCommand, Either<Notification, UpdateUserOutput>> {}


