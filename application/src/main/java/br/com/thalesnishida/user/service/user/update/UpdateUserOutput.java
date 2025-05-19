package br.com.thalesnishida.user.service.application.user.update;

import br.com.thalesnishida.user.service.domain.user.UserId;
import br.com.thalesnishida.user.service.domain.user.User;

public record UpdateUserOutput(
    UserId id
) {
    public static UpdateUserOutput from(final User aUser) {
       return new UpdateUserOutput(aUser.getId()); 
    }
}

