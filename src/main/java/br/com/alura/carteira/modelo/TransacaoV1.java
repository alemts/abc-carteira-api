package br.com.alura.carteira.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
//import java.util.Date;

public class TransacaoV1 {
    private String ticker;
    // private double preco;
    private BigDecimal preco;
    private int quantidade;
    private LocalDate data; // Date e Calendar estao em desuso
    //private String tipo;
    
    // @JsonIgnore Passa a sempre ignorar este atributo no JSON
    // Solucao: usar o padrao DTO 
    private TipoTransacao tipo;

//	public Transacao(String string, BigDecimal bigDecimal) {
//    }

    @Override
    public String toString() {
        return "Transacao [ticker=" + ticker + ", preco=" + preco + ", quantidade=" + quantidade + ", data=" + data
                + ", tipo=" + tipo + "]";
    }

    public TransacaoV1() {
        
    }

    public TransacaoV1(String ticker, LocalDate data, BigDecimal preco, int quantidade, TipoTransacao tipo) {
        this.ticker = ticker;
        this.data = data;
        this.preco = preco;
        this.quantidade = quantidade;        
        this.tipo = tipo;
    }
    
    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    
    
}
