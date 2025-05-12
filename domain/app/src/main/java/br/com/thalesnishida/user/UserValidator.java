package br.com.thalesnishida.user;

import br.com.thalesnishida.validations.Validator;
import br.com.thalesnishida.validations.Error;
import br.com.thalesnishida.validations.ValidationHandler;

public class UserValidator extends Validator {
    private final User user;

    public UserValidator(final User aUser, final ValidationHandler anHandler) {
        super(anHandler);
        this.user = aUser;
    }

    @Override
    public void validate() {
        checkName();
    }

    private void checkName() {
        final var userName = this.user.getName();

        if(userName == null) {
            this.validationHandler().append(new Error("'name' should be not null"));
            return;
        }
    }

}
