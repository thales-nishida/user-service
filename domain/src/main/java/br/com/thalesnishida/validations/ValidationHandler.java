package br.com.thalesnishida.validations;

import java.util.List;

public interface ValidationHandler {
    
    ValidationHandler append(Error anError);

    ValidationHandler append(ValidationHandler anHandler);

    ValidationHandler validate(Validation aValidation);

    List<Error> getErrors();

    default boolean hasEror() {
        return getErrors() != null && !getErrors().isEmpty();
    }

    default Error getFirstError() {
        if(getErrors() != null && !getErrors().isEmpty()) {
            return getErrors().get(0);
        }
        return null;
    }

    interface Validation {
        void validate();
    }
}
