package br.com.thalesnishida.user.service.domain;

import java.util.Objects;
import br.com.thalesnishida.user.service.domain.validations.ValidationHandler;

abstract public class Entity<ID extends Identifier> {
    protected final ID id;

    protected Entity(final ID id) {
        Objects.requireNonNull(id, "'id' should not be null");
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    public abstract void validate(ValidationHandler handler);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity<?> entity = (Entity<?>) o;
        return Objects.equals(getId(), entity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
