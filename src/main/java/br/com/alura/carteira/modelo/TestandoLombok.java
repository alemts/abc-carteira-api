package br.com.alura.carteira.modelo;

import java.math.BigDecimal;

public class TestandoLombok
{
    public static void main(String[] args)
    {
        Transacao t = new Transacao();
        t.setPreco(new BigDecimal(10.0));

        System.out.println(t);
    }
}
