package br.com.alura.carteira.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.carteira.dto.UsuarioDto;
import br.com.alura.carteira.dto.UsuarioFormDto;
import br.com.alura.carteira.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/usuarios")
@Api(tags = "Usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping
    @ApiOperation("Listar Usuarios")
    public Page<UsuarioDto> listar(@PageableDefault(size = 15) Pageable paginacao) {
        return service.listar(paginacao);
    }

    @PostMapping
    @ApiOperation("Cadastrar Usuario")
    public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioFormDto dto, UriComponentsBuilder uriBuilder) {
        UsuarioDto cadastrado = service.cadastrar(dto);
        URI endereco = uriBuilder.path("/usuarios/{id}").buildAndExpand(cadastrado.getId()).toUri();
        return ResponseEntity.created(endereco).body(cadastrado);
    }
}
