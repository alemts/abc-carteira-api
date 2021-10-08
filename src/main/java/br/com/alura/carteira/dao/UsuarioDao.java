package br.com.alura.carteira.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.alura.carteira.modelo.Usuario;

//Classe em desuso: susbtituida por UsuarioRepository
@Repository
public class UsuarioDao {

    @Autowired
    private EntityManager em;
    
    public void salvar(Usuario usuario) {
        em.persist(usuario);
    }
    
    public List<Usuario> listar() {
        return em
                .createQuery("SELECT u FROM Usuario u", Usuario.class)
                .getResultList();
    }

}
