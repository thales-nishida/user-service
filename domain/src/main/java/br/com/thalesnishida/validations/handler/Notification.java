package br.com.thalesnishida.user.service.domain.validations.handler;

import java.util.List;
import java.util.ArrayList;
import br.com.thalesnishida.user.service.domain.validations.Error;
import br.com.thalesnishida.user.service.domain.validations.ValidationHandler;
import br.com.thalesnishida.user.service.domain.exceptions.DomainException;
    
public class Notification implements ValidationHandler {
    private final List<Error> errors;

    private Notification(final List<Error> anErrors) {
        this.errors = anErrors;
    }

    public static Notification create() {
        return new Notification(new ArrayList<>());
    }

    public static Notification create(final Throwable t) {
        return create(new Error(t.getMessage()));
    }

    public static Notification create(final Error error) {
        return new Notification(new ArrayList<>()).append(error);
    }
    
    @Override
    public Notification append(Error anError) {
        this.errors.add(anError);
        return this;
    }

    @Override
    public Notification append(ValidationHandler anHandler) {
        this.errors.addAll(anHandler.getErrors());
        return this;
    }

    @Override 
    public Notification validate(final Validation aValidation) {
        try {
            aValidation.validate();
        } catch (final DomainException ex) {
            this.errors.addAll(ex.getErrors());
        } catch(final Throwable t) {
            this.errors.add(new Error(t.getMessage()));
        }
        return this;
    }

    @Override
    public List<Error> getErrors() {
        return this.errors;
    }
}
