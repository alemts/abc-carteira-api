package br.com.alura.carteira.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import br.com.alura.carteira.dto.TransacaoDto;
import br.com.alura.carteira.dto.TransacaoFormDto;
import br.com.alura.carteira.modelo.TipoTransacao;
import br.com.alura.carteira.modelo.Transacao;
import br.com.alura.carteira.modelo.Usuario;
import br.com.alura.carteira.repository.TransacaoRepository;
import br.com.alura.carteira.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {

    @Mock
    private TransacaoRepository transacaoRepository;
    
    @Mock
    private UsuarioRepository usuarioRepository;
    
    @Mock
    private ModelMapper modelMapper;
    
    @Mock
    private CalculadoraDeImpostoService calculadoraDeImpostoService;
    
    @InjectMocks
    private TransacaoService service;

    private Usuario logado;
    
    @BeforeEach
    public void before() {
        this.logado = new Usuario("Alexandre", "ale", "123");
    }
    
    private TransacaoFormDto criarTransacaoFormDto() {
        TransacaoFormDto formDto = new TransacaoFormDto(
                "PETR4",
                new BigDecimal("35.00"),
                LocalDate.now(),
                120,
                TipoTransacao.COMPRA,
                1L);
        return formDto;
    }
    
    @Test
    void deveriaCadastrarUmaTransacao() {
        TransacaoFormDto formDto = criarTransacaoFormDto();

        // Simulando cena 1
        Mockito
        .when(usuarioRepository.getById(formDto.getUsuarioId()))
        .thenReturn(logado);
        
        // Simulando cena 2
        Transacao transacao = new Transacao(
                formDto.getTicker(), 
                formDto.getPreco(),
                formDto.getQuantidade(),
                formDto.getData(),
                formDto.getTipo(),
                logado);
        Mockito
        .when(modelMapper.map(formDto, Transacao.class))
        .thenReturn(transacao);

        // Simulando cena 3
        Mockito
        .when(modelMapper.map(transacao, TransacaoDto.class))
        .thenReturn(new TransacaoDto(
                null,
                transacao.getTicker(),
                transacao.getPreco(),
                transacao.getQuantidade(),
                transacao.getTipo(),
                BigDecimal.ZERO));

        // Testando
        TransacaoDto dto = service.cadastrar(formDto, logado);
        Mockito.verify(transacaoRepository).save(Mockito.any());
        
        assertEquals(formDto.getTicker(), dto.getTicker());
        assertEquals(formDto.getPreco(), dto.getPreco());
        assertEquals(formDto.getQuantidade(), dto.getQuantidade());
        assertEquals(formDto.getTipo(), dto.getTipo());
    }

    @Test
    void naoDeveriaCadastrarUmaTransacaoComUsuarioInexistente() {
        TransacaoFormDto formDto = criarTransacaoFormDto();

        Mockito
        .when(usuarioRepository.getById(formDto.getUsuarioId()))
        .thenThrow(EntityNotFoundException.class);

        assertThrows(IllegalArgumentException.class, () -> service.cadastrar(formDto, logado));
    }
}
