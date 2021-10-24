package br.com.alura.carteira.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString(exclude = {"data", "quantidade", "tipo"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transacoes")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Getter @Setter
    //@Column(name = "tck")
    private String ticker;
    private BigDecimal preco;
    private Integer quantidade;
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;

    //@JoinColumn(name = "id_usuario") //Define o nome da coluna
    //@ManyToOne = cria no padrao: usuario_id.
    @ManyToOne   
    private Usuario usuario;

    public Transacao(String ticker, BigDecimal preco, Integer quantidade, 
            LocalDate data, TipoTransacao tipo, Usuario usuario) {
        this.ticker = ticker;
        this.preco = preco;
        this.quantidade = quantidade;
        this.data = data;
        this.tipo = tipo;
        this.usuario = usuario;
    }
}
