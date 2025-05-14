package br.com.thalesnishida.user.service.domain.user;

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
        checkPassword();
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

    private void checkPassword() {
        final var password = this.user.getPassword();
        final var regexPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";

        if(password == null) {
            this.validationHandler().append(new Error("'password' should be not null"));
            return;
        }

        if(password.length() < 2 || password.length() > 20) {
            this.validationHandler().append(new Error("'password' should be contains at least 8 characters and at most 20 characters"));
            return;
        }

        if(Pattern.compile(regexPattern).matcher(password).find() == false) {
            this.validationHandler().append(new Error("'password' should be contains at least one digit, uppercase, lowercase, special character"));
            return;
        }
    }

}
