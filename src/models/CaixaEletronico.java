package models;

import services.impl.MockServicoRemoto;

public class CaixaEletronico {

    private ContaCorrente contaCorrente;

    public CaixaEletronico(){

    }

    public String saldo(String numeroConta, MockServicoRemoto mockServicoRemoto){
        contaCorrente = mockServicoRemoto.recuperarConta(numeroConta);

        if(contaCorrente != null)
            return "O saldo é R$ " + contaCorrente.getSaldo();

        return "Não foi possível localizar a conta corrente";
    }

    public String sacar(String numeroConta, MockServicoRemoto mockServicoRemoto, double valorSaque){
        contaCorrente = mockServicoRemoto.recuperarConta(numeroConta);

        if((contaCorrente.getSaldo() - valorSaque)  > 0) {
            mockServicoRemoto.persistirConta(numeroConta, contaCorrente.getSaldo() - valorSaque);
            return "Retire seu dinheiro";
        }
        return "Saldo insuficiente";
    }

    public String depositar(String numeroConta, MockServicoRemoto mockServicoRemoto, double valorDeposito){
        contaCorrente = mockServicoRemoto.recuperarConta(numeroConta);

        if(valorDeposito > 0){
            mockServicoRemoto.persistirConta(numeroConta, contaCorrente.getSaldo() + valorDeposito);
            return "Deposito recebido com sucesso";
        }
        return "Não foi possível realizar o deposito";
    }

    public String login(String numeroConta, MockServicoRemoto mockServicoRemoto){
        contaCorrente = mockServicoRemoto.recuperarConta(numeroConta);

        if(numeroConta != null){
            return "Usuário Autenticado";
        }
        return "Não foi possível autenticar o usuário";
    }
}
