package br.com.alura.carteira.controller;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class UsuarioControllerTest {

    // Simulando requisicoes Http
    @Autowired
    private MockMvc mvc;

    /*
     * Testar se vai devolver o codigo 400
     */
    @Test
    void naoDeveriaCadastrarUsuarioComDadosIncompletos() throws Exception {
        String json = "{}";

        mvc
        .perform(
                post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
        .andExpect(status().isBadRequest());
    }

    /*
     * Testar se vai devolver o codigo 201 com cabecalho Location (endereco e
     * recurso)
     */
    @Test
    void naoDeveriaCadastrarUsuarioComDadosCompletos() throws Exception {
        String json    = "{ \"nome\"  :\"Fulano\",           " +
                         "  \"login\" :\"fulano@email.com\" }";

        String jsonRet = "{\"nome\":\"Fulano\",\"login\":\"fulano@email.com\"}";

        mvc
        .perform(
                post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
        .andExpect(status().isCreated())
        .andExpect(header().exists("Location"))
        .andExpect(content().json(jsonRet));
    }

}
