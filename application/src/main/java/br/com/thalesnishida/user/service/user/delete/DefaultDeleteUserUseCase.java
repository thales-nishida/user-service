package br.com.thalesnishida.user.service.application.user.delete;

import br.com.thalesnishida.user.service.application.UnitUseCase;
import br.com.thalesnishida.user.service.domain.user.UserGateway;
import br.com.thalesnishida.user.service.domain.user.UserId;
import java.util.Objects;

public class DefaultDeleteUserUseCase extends UnitUseCase<String>{
    
    private final UserGateway userGateway;

    public DefaultDeleteUserUseCase(final UserGateway userGateway) {
        this.userGateway = Objects.requireNonNull(userGateway);
    }

    @Override 
    public void execute(final String anIn) {
        this.userGateway.deleteById(UserId.from(anIn));
    }
    
}

