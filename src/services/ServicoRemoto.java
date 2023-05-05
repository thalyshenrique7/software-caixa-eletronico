package services;

import models.ContaCorrente;

public interface ServicoRemoto {

    ContaCorrente recuperarConta(String numeroConta);
    void persistirConta(String numeroConta, double saldo);
}
