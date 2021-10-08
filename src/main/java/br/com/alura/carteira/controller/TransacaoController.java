package br.com.alura.carteira.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.carteira.dto.TransacaoDto;
import br.com.alura.carteira.dto.TransacaoFormDto;
import br.com.alura.carteira.service.TransacaoService;

//@Controller // @WebServlet quem gerenciava era o Tomcat
@RestController // Evita o ResponseBody nos metodos
@RequestMapping("/transacoes") // URI = endereco da requisicao
public class TransacaoController
{
    // @ResponseBody
    // public String listar()
    // @GetMapping // Se a requisicao HTTP for do tp GET
    // @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    /*
     * @GetMapping public Transacao listar() { // retorna um String pq tem q ter
     * alguma resposta //return "Hello World!";
     * 
     * return new Transacao("XPTO1", LocalDate.now(), new BigDecimal("100"), 50,
     * TipoTransacao.COMPRA); }
     */

    /*
     * @PostMapping public void cadastrar(HttpServletRequest request) { String
     * ticker = request.getParameter("ticker"); }
     */

    /*
     * Versao 2: ********************************************* private
     * List<Transacao> transacoes = new ArrayList<>();
     * 
     * @GetMapping public List<Transacao> listar() { return transacoes; }
     * 
     * @PostMapping public void cadastrar(@RequestBody Transacao transacao) {
     * transacoes.add(transacao); }
     ***********************************************************
     */

    // Versao 3: **********************************************
    //private List<Transacao> transacoes = new ArrayList<>();
    //private ModelMapper modelMapper = new ModelMapper();
    
    //Versao 4:
    @Autowired
    private TransacaoService service;
    

    @GetMapping
    public List<TransacaoDto> listar()
    {
        /*/*********************************************************
        Versao 1: Tradicional
        List<TransacaoDto> transacoesDto = new ArrayList<>();
        for (Transacao transacao : transacoes) 
        {
            TransacaoDto dto = new TransacaoDto();
            dto.setTicker(transacao.getTicker());
            dto.setPreco(transacao.getPreco());
            dto.setQuantidade(transacao.getQuantidade());
            dto.setTipo(transacao.getTipo());

            transacoesDto.add(dto);
        }
        
        return transacoesDto;*/
        
        /********************************************************* 
        Versao 2: Recurso do Java 8
        return transacoes.stream().map(TransacaoDto::new).collect(Collectors.toList());
        */
        
        /*/*******************************************************
        Versao 3: Codigo copiado da biblioteca de terceiros: ModelMapper
        ModelMapper modelMapper = new ModelMapper();
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class); */
        
        // Usando o ModelMapper:
        //ModelMapper modelMapper = new ModelMapper(); colocando como atributo da classe
        //ou
//        return transacoes
//                .stream()
//                .map(t -> modelMapper.map(t, TransacaoDto.class))
//                .collect(Collectors.toList());
        return service.listar();
    }

    // Com o @Valid e as anotações de validacao do Spring nao ha nec de validacao com if..else..
    @PostMapping
    public void cadastrar(@RequestBody @Valid TransacaoFormDto dto)
    {
//        Transacao transacao = new Transacao(
//            dto.getTicker(), 
//            dto.getData(), 
//            dto.getPreco(), 
//            dto.getQuantidade(), 
//            dto.getTipo() );
        
        // Usando o ModelMapper:
        //ModelMapper modelMapper = new ModelMapper(); colocando como atributo da classe
        //ou
//        Transacao transacao = modelMapper.map(dto, Transacao.class);
//            
//        transacoes.add(transacao);
        service.cadastrar(dto);
    }
}
