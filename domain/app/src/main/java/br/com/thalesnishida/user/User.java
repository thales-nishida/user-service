package br.com.thalesnishida.user;

public class User {
    private String name;
    private String email;
    private String password;

    private User(
            final String aName,
            final String aEmail,
            final String aPassword
    ) {
        this.name = aName;
        this.email = aEmail;
        this.password = aPassword;
    }
    
    public static User newUser(
            final String aName,
            final String aEmail,
            final String aPassword
    ) {
        return new User(aName, aEmail, aPassword);
    }
    
}

