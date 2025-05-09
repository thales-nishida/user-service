package br.com.thalesnishida.user;

import br.com.thalesnishida.AggregateRoot;

public class User extends AggregateRoot<UserId> {
    private String name;
    private String email;
    private String password;

    private User(
            final UserId anId,
            final String aName,
            final String aEmail,
            final String aPassword
    ) {
        super(anId);
        this.name = aName;
        this.email = aEmail;
        this.password = aPassword;
    }
    
    public static User newUser(
            final String aName,
            final String aEmail,
            final String aPassword
    ) {
        final var id = UserId.unique();
        return new User(id, aName, aEmail, aPassword);
    }

    public UserId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    
}

