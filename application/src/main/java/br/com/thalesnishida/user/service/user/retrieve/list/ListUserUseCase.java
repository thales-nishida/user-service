package br.com.thalesnishida.user.service.application.user.retrieve.list;

import br.com.thalesnishida.user.service.application.UseCase;
import br.com.thalesnishida.user.service.domain.pagination.Pagination;
import br.com.thalesnishida.user.service.domain.user.UserSearchQuery;

public abstract class ListUserUseCase extends UseCase<UserSearchQuery, Pagination<UserListOutput>> {}
