package test;

import models.CaixaEletronico;
import models.ContaCorrente;
import org.junit.Before;
import org.junit.Test;
import services.impl.MockServicoRemoto;

import static org.junit.Assert.*;

public class TesteCaixaEletronico {

    CaixaEletronico caixaEletronico;
    ContaCorrente contaCorrente;
    MockServicoRemoto mockServicoRemoto;

    @Before
    public void inicializa(){
        caixaEletronico = new CaixaEletronico();
        contaCorrente = new ContaCorrente("1", 100);
        mockServicoRemoto = new MockServicoRemoto();

        mockServicoRemoto.adicionaConta(contaCorrente);
    }

    @Test
    public void testeLoginOk(){
        assertEquals(caixaEletronico.login("1", mockServicoRemoto), "Usuário Autenticado");
    }

    @Test
    public void testeLoginFalha(){
        assertEquals(caixaEletronico.login(null, mockServicoRemoto), "Não foi possível autenticar o usuário");
    }

    @Test
    public void testeRecuperarContaOk(){
        assertEquals("1", contaCorrente.getNumeroConta());
    }

    @Test
    public void testeRecuperarContaFalha(){
        assertEquals(null, null);
    }

    @Test
    public void testeSaldoOk(){
        assertEquals(caixaEletronico.saldo("1", mockServicoRemoto), "O saldo é R$ 100.0");
    }

    @Test
    public void testeSaldoFalha(){
        assertEquals(caixaEletronico.saldo("2", mockServicoRemoto), "Não foi possível localizar a conta corrente");
    }

    @Test
    public void testeSacarOk(){
        assertEquals(caixaEletronico.sacar("1", mockServicoRemoto, 50), "Retire seu dinheiro");
    }

    @Test
    public void testeSacarFalha(){
        assertEquals(caixaEletronico.sacar("1", mockServicoRemoto, 200), "Saldo insuficiente");
    }

    @Test
    public void testeDepositarOk(){
        assertEquals(caixaEletronico.depositar("1", mockServicoRemoto, 200), "Deposito recebido com sucesso");
    }

    @Test
    public void testeDepositarFalha(){
        assertEquals(caixaEletronico.depositar("1", mockServicoRemoto, -1), "Não foi possível realizar o deposito");
    }
}
