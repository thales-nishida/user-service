package br.com.thalesnishida.user.service.domain;

import br.com.thalesnishida.user.service.domain.validations.ValidationHandler;

public class AggregateRoot<ID extends Identifier> extends Entity<ID> {

    protected AggregateRoot(final ID id) {
        super(id);
    }

    @Override
    public void validate(ValidationHandler handler) {}
}
