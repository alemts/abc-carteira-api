package br.com.alura.carteira.dto;

import java.math.BigDecimal;

import br.com.alura.carteira.modelo.TipoTransacao;
import lombok.Getter;
import lombok.Setter;

// Eh uma classe Java pura: POJO
@Getter @Setter
public class TransacaoDto
{
    private String ticker;
    private BigDecimal preco;
    private Integer quantidade;
    private TipoTransacao tipo;

    // Comentado pq TransacaoController.listar() passou a usar o ModelMapper
    // Construtor que recebe o obj de dominio
//    public TransacaoDto(Transacao transacao)
//    {
//        this.ticker = transacao.getTicker();
//        this.preco = transacao.getPreco();
//        this.quantidade = transacao.getQuantidade();
//        this.tipo = transacao.getTipo();
//    }


}
