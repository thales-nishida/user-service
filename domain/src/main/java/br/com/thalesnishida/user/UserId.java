package br.com.thalesnishida.user.service.domain.user;

import br.com.thalesnishida.user.service.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class UserId extends Identifier {

    protected final String value;

    private UserId(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }
    
    public static UserId unique() {
        return new UserId(UUID.randomUUID().toString());
    }

    public static UserId from(final String anId) {
        return new UserId(anId);
    }

    public static UserId from(final UUID anId) {
        return new UserId(anId.toString().toLowerCase());
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId = (UserId) o;
        return Objects.equals(value, userId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
