package br.com.thalesnishida.user;

import java.util.regex.Pattern;
import java.util.regex.MatchResult;
import br.com.thalesnishida.validations.Validator;
import br.com.thalesnishida.validations.Error;
import br.com.thalesnishida.validations.ValidationHandler;

public class UserValidator extends Validator {

    public static final int MIN_LENGTH = 2;
    public static final int MAX_LENGTH = 255;
    private final User user;

    public UserValidator(final User aUser, final ValidationHandler anHandler) {
        super(anHandler);
        this.user = aUser;
    }

    @Override
    public void validate() {
        checkName();
        checkEmail();  
    }

    private void checkName() {
        final var userName = this.user.getName();

        if(userName == null) {
            this.validationHandler().append(new Error("'name' should be not null"));
            return;
        }

        if(userName.isEmpty()) {
            this.validationHandler().append(new Error("'name' should be not empty"));
            return;
        }

        if(userName.length() < MIN_LENGTH || userName.length() > MAX_LENGTH) {
            this.validationHandler().append(new Error("'name' should be between 2 and 255 characters"));
            return;
        }
    }

    private void checkEmail() {
        final var email = this.user.getEmail();
        final var regexPattern = "^(.+)@(\\S+)$";

        if(email == null) {
            this.validationHandler().append(new Error("'email' should be not null"));
            return;
        }

        if(email.isEmpty()) {
            this.validationHandler().append(new Error("'email' should be not empty"));
            return;
        }

        if(Pattern.compile(regexPattern).matcher(email).find() == false) {
            this.validationHandler().append(new Error("'email' format invalid"));
            return;
        }
        
    }  

}
