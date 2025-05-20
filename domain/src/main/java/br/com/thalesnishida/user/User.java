package br.com.thalesnishida.user.service.domain.user;

import br.com.thalesnishida.user.service.domain.AggregateRoot;
import br.com.thalesnishida.user.service.domain.validations.ValidationHandler;

import java.time.Instant;
import java.lang.Cloneable;
import java.lang.CloneNotSupportedException;

public class User extends AggregateRoot<UserId> implements Cloneable {
    private String name;
    private String email;
    private String password;
    private Instant updatedAt;
    private final Instant createdAt;

    private User(
            final UserId anId,
            final String aName,
            final String aEmail,
            final String aPassword,
            final Instant aCreationDate,
            final Instant aUpdatedAt
    ) {
        super(anId);
        this.name = aName;
        this.email = aEmail;
        this.password = aPassword;
        this.createdAt = aCreationDate;
        this.updatedAt = aUpdatedAt;
    }
    
    public static User newUser(
            final String aName,
            final String aEmail,
            final String aPassword
    ) {
        final var id = UserId.unique();
        final var now = Instant.now();
        return new User(id, aName, aEmail, aPassword, now, now);
    }

    public User update(
            final String aName,
            final String aEmail,
            final String aPassword
    ) {
        this.name = aName;
        this.password = aPassword;
        this.email = aEmail;
        this.updatedAt = Instant.now();
        return this;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new UserValidator(this, handler).validate();
    } 

    @Override
    public User clone() {
        try {
            User clone = (User) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

