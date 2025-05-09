package br.com.thalesnishida.validation.handler;

import br.com.thalesnishida.exception.DomainExecption;
import br.com.thalesnishida.validation.ValidationHandler;
import br.com.thalesnishida.validation.Error;

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
    }

    @Override
    public List<Error> getErrors() {
        return List.of();
    }


}
