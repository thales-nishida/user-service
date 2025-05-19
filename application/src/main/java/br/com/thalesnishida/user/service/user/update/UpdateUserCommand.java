package br.com.thalesnishida.user.service.application.user.update;

public record UpdateUserCommand(
    String id,
    String name,
    String email,
    String password
) {

    public static UpdateUserCommand with(
            final String anId,
            final String aName,
            final String aEmail,
            final String aPassword
    ) {
        return new UpdateUserCommand(anId, aName, aEmail, aPassword);
    }
    
}
