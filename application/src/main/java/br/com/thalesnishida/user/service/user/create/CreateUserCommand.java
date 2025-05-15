package br.com.thalesnishida.user.service.application.user.create;

public record CreateUserCommand (
    String name,
    String email,
    String password
) {
    public static CreateUserCommand with(
            String aName,
            String aEmail,
            String aPassword
    ) {
        return new CreateUserCommand(aName, aEmail, aPassword);
    }

}
