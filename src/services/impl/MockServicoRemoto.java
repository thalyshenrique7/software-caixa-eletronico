package services.impl;

import exceptions.PegarNumeroDaContaCartaoException;
import models.CaixaEletronico;
import models.ContaCorrente;
import org.junit.Before;
import org.junit.Test;
import services.ServicoRemoto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MockServicoRemoto implements ServicoRemoto {

    private List<ContaCorrente> listaDeContas = new ArrayList<>();

    public void adicionaConta(ContaCorrente cc){
        listaDeContas.add(cc);
    }

    @Override
    public ContaCorrente recuperarConta(String numeroConta) {
        for(ContaCorrente cc : listaDeContas){
            if(cc.getNumeroConta().equals(numeroConta)){
                return cc;
            }
        }
        return null;
    }

    @Override
    public void persistirConta(String numeroConta, double saldo) {
        ContaCorrente cc;
        cc = recuperarConta(numeroConta);
        if(cc != null){
            cc.setSaldo(saldo);
        }
    }

    @Before
    public void inicializa(){
        listaDeContas.add(new ContaCorrente("1", 200));
    }

    @Test
    public void testeRecuperarContaOk(){
        ContaCorrente cc;
        cc = recuperarConta("1");

        assertEquals("1", cc.getNumeroConta());
    }

    @Test
    public void testeRecuperarContaFalha(){
        ContaCorrente cc;
        cc = recuperarConta("2");

        assertNull(cc);
    }

    @Test
    public void testePersistirContaOk(){
        CaixaEletronico ce = new CaixaEletronico();
        assertEquals("Deposito recebido com sucesso", ce.depositar("1", this, 150));
        ContaCorrente cc = recuperarConta("1");
        assertEquals(cc.getSaldo(), 350, 0);
    }

    @Test
    public void testePersistirContaFalha(){
        CaixaEletronico ce = new CaixaEletronico();
        assertEquals("Não foi possível realizar o deposito", ce.depositar("1", this, 0));
        ContaCorrente cc = recuperarConta("1");
        assertEquals(cc.getSaldo(), 200, 0);
    }
}
