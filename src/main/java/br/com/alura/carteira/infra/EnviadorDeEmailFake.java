package br.com.alura.carteira.infra;

import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Profile("default")
public class EnviadorDeEmailFake implements EnviadorDeEmail {

    @Override
    @Async
    public void enviarEmail(String destinatario, String assunto, String mensagem) {
        System.out.println("Enviando EMAIL:");
        System.out.println("Destinatario: " + destinatario);
        System.out.println("Assunto: " + assunto);
        System.out.println("Mensage,: " + mensagem);
    }
}