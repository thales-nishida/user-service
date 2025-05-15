package br.com.thalesnishida.user.service.domain.validations.handler;

import br.com.thalesnishida.user.service.domain.exceptions.DomainException;
import br.com.thalesnishida.user.service.domain.validations.ValidationHandler;
import br.com.thalesnishida.user.service.domain.validations.Error;

import java.util.List;

public class ThrowValidationHandler implements ValidationHandler {
    @Override
    public ValidationHandler append(Error anError) {
        throw DomainException.with(anError);
    }

    @Override
    public ValidationHandler append(ValidationHandler anHandler) {
        throw DomainException.with(anHandler.getErrors());
    }

    @Override 
    public ValidationHandler validate(final Validation aValidation) {
        try {
            aValidation.validate();
        } catch (final Exception ex) {
            throw DomainException.with(new Error(ex.getMessage()));
        }
        return this;
    }

    @Override
    public List<Error> getErrors() {
        return List.of();
    }


}
