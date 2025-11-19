package com.conversorDeMoedas;
import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;

import static java.lang.System.*;

public class Main {

    static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(in);
        int opcao = 0;
        double valor_inicial = 0;
        boolean entradaValida = false;



        String menu = """
                *********************************
                Seja bem-vindo ao Conversor de moedas!
                
                Selecione umas das opções de conversão:
                
                1 - Dólar Neozelandês --> Real
                2 - Real --> Dólar Neozelandês
                3 - Renmimbi --> Real
                4 - Real --> Renmimbi
                5 - Euro --> Real
                6 - Real --> Euro
                7 - Sair
                
                *********************************
                """;

        while (!entradaValida) {
            try {
                out.println(menu);
                opcao = leitura.nextInt();
                if (opcao >= 1 && opcao <= 6) {
                    entradaValida = true;
                }else if (opcao == 7){
                    out.println("Processo de conversão encerrado.");
                    break;
                }
            } catch (InputMismatchException e) {
                out.println("Valor inválido!");
                leitura.next();
            }

            entradaValida = false;

            while (!entradaValida){
                try {
                    out.println("Digite o valor que deseja converter:");
                    valor_inicial = leitura.nextDouble();

                    if (valor_inicial <=0){
                        out.println("O valor deve ser maior que 0.");
                    } else{
                        entradaValida = true;
                    }
                } catch (InputMismatchException e){
                    out.println("Valor inválido!!");
                    leitura.next();
                }
            }
        }
        leitura.close();

        Conversor conversor = new Conversor();
        String moedas = Conversor.converter_moedas(opcao);
        double valor = conversor.receber_valor(valor_inicial);
        String url_completa = Conversor.construtor_url(valor, moedas);
        double valor_resultado = Conversor.resultado_da_conversao(url_completa);

        String mensagem_final = mensagem_resposta(moedas, valor, valor_resultado);
        System.out.println(mensagem_final);

    }

    private static String mensagem_resposta(String moedas, double valor, double valor_resultado) {
        String mensagem;
        switch (moedas) {
            case "BRL/NZD/" -> {
                mensagem = String.format("O valor da conversão de BRL " + valor + " é de NZD " + valor_resultado);
                break;
            }
            case "NZD/BRL/" -> {
                mensagem = String.format("O valor da conversão de NZD " + valor + " é de BRL " + valor_resultado);
                break;
            }
            case "CNY/BRL/" -> {
                mensagem = String.format("O valor da conversão de CNY " + valor + " é de BRL " + valor_resultado);
                break;
            }
            case "BRL/CNY/" -> {
                mensagem = String.format("O valor da conversão de BRL " + valor + " é de CNY " + valor_resultado);
                break;
            }
            case "EUR/BRL/" -> {
                mensagem = String.format("O valor da conversão de EUR " + valor + " é de BRL " + valor_resultado);
                break;
            }
            case "BRL/EUR/" -> {
                mensagem = String.format("O valor da conversão de BRL " + valor + " é de EUR " + valor_resultado);
                break;
            }
            default -> {
                mensagem = String.format("Erro na conversão.");
                break;
            }
        }
        return mensagem;
    }
}
