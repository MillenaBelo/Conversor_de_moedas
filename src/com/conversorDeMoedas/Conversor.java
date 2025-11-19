package com.conversorDeMoedas;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Conversor {

    public double receber_valor(double valor_inicial) {
        return valor_inicial;
    }

    public static String converter_moedas(int opcao) throws IOException, InterruptedException {
        return switch (opcao) {
            case 1 -> "BRL/NZD/";
            case 2 -> "NZD/BRL/";
            case 3 -> "CNY/BRL/";
            case 4 -> "BRL/CNY/";
            case 5 -> "EUR/BRL/";
            case 6 -> "BRL/EUR/";
            default -> "BRL/BRL/";
        };
    }

    public static String construtor_url(double valor, String moedas) throws IOException, InterruptedException {
        return Dados.getEndereco() + Dados.getChave() + Dados.getUrl_meio() + moedas + valor;
    }

    public static double resultado_da_conversao (String url_completa) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url_completa))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonElement elemento = JsonParser.parseString((response.body()));
        JsonObject object = elemento.getAsJsonObject();

        double resultado = object.get("conversion_result").getAsDouble();
        return resultado;
    }
}
