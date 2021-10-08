package br.com.alura.carteira.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.carteira.modelo.Transacao;

// Nao precisa criar uma classe q implementa esta interface,
// o proprio Spring gera. Alem disso, por ser uma interface 
// nao precisa de anotacao
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

}
