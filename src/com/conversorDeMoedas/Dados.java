package com.conversorDeMoedas;

public class Dados {
    private static final String endereco = "https://v6.exchangerate-api.com/v6/";
    private static final String chave = "26ecbb842feb25f42a595fa4";
    private static final String url_meio = "/pair/";

    public static String getEndereco() {
        return endereco;
    }

    public static String getChave() {
        return chave;
    }

    public static String getUrl_meio() {
        return url_meio;
    }
}
