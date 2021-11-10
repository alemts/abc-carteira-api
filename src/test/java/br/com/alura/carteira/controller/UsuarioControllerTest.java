package br.com.alura.carteira.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.carteira.infra.security.TokenService;
import br.com.alura.carteira.modelo.Perfil;
import br.com.alura.carteira.modelo.Usuario;
import br.com.alura.carteira.repository.PerfilRepository;
import br.com.alura.carteira.repository.UsuarioRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class UsuarioControllerTest {

    // Simulando requisicoes Http
    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private PerfilRepository perfilRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    private String token;

    @BeforeEach
    private void gerarToken() {
        Usuario logado = new Usuario("Alexandre", "ale", "123");
        Perfil admin = perfilRepository.findById(1L).get();
        logado.adicionarPerfil(admin);
        usuarioRepository.save(logado);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                logado, logado.getLogin());
        this.token = tokenService.gerarToken(auth);
    }
    
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
                .content(json)
                .header("Authorization", "Bearer " + token))
        .andExpect(status().isBadRequest());
    }

    /*
     * Testar se vai devolver o codigo 201 com cabecalho Location 
     * (endereco e recurso)
     */
    @Test
    void deveriaCadastrarUsuarioComDadosCompletos() throws Exception {
        // Envia obj UsuarioFormDto em formato Json
        String json    = "{ \"nome\"     :\"Fulano\",           " +
                         "  \"login\"    :\"fulano@email.com\", " +
                         "  \"perfilId\" :1 }";

        // Recebe em formato Json a representacao do obj UsuarioDto
        String jsonRet = "{\"nome\":\"Fulano\",\"login\":\"fulano@email.com\"}";

        mvc
        .perform(
                post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .header("Authorization", "Bearer " + token))
        .andExpect(status().isCreated())
        .andExpect(header().exists("Location"))
        .andExpect(content().json(jsonRet));
    }

}
