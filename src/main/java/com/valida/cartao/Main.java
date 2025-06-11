package com.valida.cartao;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Validador de Bandeiras de Cartão de Crédito ===");
        System.out.println("Digite o número do cartão para identificar a bandeira");
        System.out.println("(Digite 'sair' para encerrar)\n");
        
        while (true) {
            System.out.print("Número do cartão: ");
            String input = scanner.nextLine().trim();
            
            // Verificar se usuário quer sair
            if (input.equalsIgnoreCase("sair")) {
                System.out.println("Encerrando o programa...");
                break;
            }
            
            // Verificar se entrada está vazia
            if (input.isEmpty()) {
                System.out.println("⚠️  Por favor, digite um número de cartão ou 'sair' para encerrar.\n");
                continue;
            }
            
            // Validar e processar entrada
            try {
                String result = validateAndIdentify(input);
                System.out.println(result + "\n");
            } catch (Exception e) {
                System.out.println("❌ Erro inesperado: " + e.getMessage() + "\n");
            }
        }
        
        scanner.close();
    }
    
    private static String validateAndIdentify(String input) {
        // Remover todos os caracteres não numéricos
        String sanitized = input.replaceAll("\\D", "");
        
        // Verificar se sobrou algum dígito após sanitização
        if (sanitized.isEmpty()) {
            return "❌ Entrada inválida: Não foram encontrados números na entrada '" + input + "'";
        }
        
        // Verificar comprimento mínimo e máximo
        if (sanitized.length() < 8) {
            return "❌ Número muito curto: " + sanitized.length() + " dígitos. Cartões válidos têm entre 8 e 19 dígitos.";
        }
        
        if (sanitized.length() > 19) {
            return "❌ Número muito longo: " + sanitized.length() + " dígitos. Cartões válidos têm entre 8 e 19 dígitos.";
        }
        
        // Verificar se contém apenas zeros
        if (sanitized.matches("^0+$")) {
            return "❌ Número inválido: Não pode conter apenas zeros.";
        }
        
        // Identificar bandeira
        CardBrandIdentifier.CardBrand brand = CardBrandIdentifier.identify(sanitized);
        
        if (brand == CardBrandIdentifier.CardBrand.UNKNOWN) {
            return "❓ Bandeira não identificada para o número: " + formatCardNumber(sanitized) + 
                   "\n   O número possui " + sanitized.length() + " dígitos e não corresponde aos padrões conhecidos.";
        }
        
        return "✅ Bandeira identificada: " + brand + " para o número: " + formatCardNumber(sanitized);
    }
    
    private static String formatCardNumber(String cardNumber) {
        // Formatar número para exibição (mascarar dígitos do meio)
        if (cardNumber.length() <= 8) {
            return cardNumber.substring(0, 4) + "****" + cardNumber.substring(cardNumber.length() - 4);
        }
        return cardNumber.substring(0, 4) + " **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }
}