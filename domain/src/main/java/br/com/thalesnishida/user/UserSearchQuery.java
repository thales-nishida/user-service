package br.com.thalesnishida.user.service.domain.user;

public record UserSearchQuery(
    int paga,
    int perPage,
    String terms,
    String sort,
    String direction
){}
