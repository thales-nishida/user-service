package br.com.thalesnishida.user.service.application;

public abstract class UseCase<IN, OUT> {

    public abstract OUT execute(IN anIN);

}
