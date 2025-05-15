package br.com.thalesnishida.user.service.application.user.create;

import br.com.thalesnishida.user.service.domain.user.UserId; 
import br.com.thalesnishida.user.service.domain.user.User;

public record CreateUserOutput(UserId id) {
    public static CreateUserOutput from(final User aUser) {
        return new CreateUserOutput(aUser.getId());
    }
}
