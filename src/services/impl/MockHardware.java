package services.impl;

import exceptions.EntregarDinheiroException;
import exceptions.LerEnvelopeException;
import exceptions.PegarNumeroDaContaCartaoException;
import org.junit.Test;
import services.Hardware;

public class MockHardware implements Hardware {

    private boolean falhaNumeroDaContaCartao = false;
    private boolean falhaEntregarDinheiro = false;
    private boolean falhaLerEnvelope = false;
    private String contaCorrente = "1";

    @Override
    public String pegarNumeroDaContaCartao(String numeroConta) {
        if(this.isFalhaNumeroDaContaCartao())
            throw new PegarNumeroDaContaCartaoException("Falha ao tentar pegar número da conta");

        return this.contaCorrente;
    }

    @Override
    public void entregarDinheiro() {
        if(this.falhaEntregarDinheiro)
            throw new EntregarDinheiroException("Falha ao tentar entregar o dinheiro");
    }

    @Override
    public void lerEnvelope() {
        if(this.falhaLerEnvelope)
            throw new LerEnvelopeException("Falha ao tentar ler o envelope");
    }

    public boolean isFalhaNumeroDaContaCartao() {
        return falhaNumeroDaContaCartao;
    }

    public void setFalhaNumeroDaContaCartao(boolean falhaNumeroDaContaCartao) {
        this.falhaNumeroDaContaCartao = falhaNumeroDaContaCartao;
    }

    public boolean isFalhaEntregarDinheiro() {
        return falhaEntregarDinheiro;
    }

    public void setFalhaEntregarDinheiro(boolean falhaEntregarDinheiro) {
        this.falhaEntregarDinheiro = falhaEntregarDinheiro;
    }

    public boolean isFalhaLerEnvelope() {
        return falhaLerEnvelope;
    }

    public void setFalhaLerEnvelope(boolean falhaLerEnvelope) {
        this.falhaLerEnvelope = falhaLerEnvelope;
    }

    @Test(expected = PegarNumeroDaContaCartaoException.class)
    public void testePegarNumeroDaContaCartaoOk(){
        setFalhaNumeroDaContaCartao(true);
        pegarNumeroDaContaCartao(null);
    }

    @Test
    public void testePegarNumeroDaContaCartaoFalha(){
        setFalhaNumeroDaContaCartao(false);
        pegarNumeroDaContaCartao(null);
    }

    @Test(expected = EntregarDinheiroException.class)
    public void testeEntregarDinheiroOk(){
        setFalhaEntregarDinheiro(true);
        entregarDinheiro();
    }

    @Test
    public void testeEntregarDinheiroFalha(){
        setFalhaEntregarDinheiro(false);
        entregarDinheiro();
    }

    @Test(expected = LerEnvelopeException.class)
    public void testeLerEnvelopeOk(){
        setFalhaLerEnvelope(true);
        lerEnvelope();
    }

    @Test
    public void testeLerEnvelopeFalha(){
        setFalhaLerEnvelope(false);
        lerEnvelope();
    }
}
