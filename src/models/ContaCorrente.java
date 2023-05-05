package models;

public class ContaCorrente {

    private String numeroConta;
    private double saldo;

    public ContaCorrente(){}

    public ContaCorrente(String numeroConta, double saldo) {
        this.numeroConta = numeroConta;
        this.saldo = saldo;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
