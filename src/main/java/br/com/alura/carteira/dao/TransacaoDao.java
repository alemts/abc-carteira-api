package br.com.alura.carteira.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.alura.carteira.modelo.Transacao;

// Classe em desuso: susbtituida por TransacaoRepository
@Repository
public class TransacaoDao {

    @Autowired
    private EntityManager em; //Este eh meio q o substituo da Connection
    
    public void salvar(Transacao transacao) {
        em.persist(transacao);
    }
    
    public List<Transacao> listar() {
        return em
                .createQuery("SELECT t FROM Transacao t", Transacao.class)
                .getResultList();
    }

}
