package com.valida.cartao;

public class Main {    public static void main(String[] args) {
        System.out.println("=== Teste de Validação ===");        // Teste rápido
        String[] testCards = {
            "4111111111111111",    // Visa (16 dígitos)
            "378282246310005",     // American Express (15 dígitos)
            "3760 133531 67637"    // Número que estava causando erro
        };
        
        for (String card : testCards) {
            try {
                CardBrandIdentifier.CardBrand brand = CardBrandIdentifier.identify(card);
                System.out.println("Cartão " + card + " -> " + brand);
            } catch (Exception e) {
                System.out.println("ERRO com cartão " + card + ": " + e.getMessage());
            }
        }
    }
}