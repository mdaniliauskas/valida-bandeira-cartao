package com.valida.cartao;

import java.util.Scanner;

/**
 * Classe principal da aplicação de validação de bandeiras de cartão de crédito.
 * 
 * Fornece uma interface de linha de comando interativa para que os usuários
 * possam inserir números de cartão e identificar suas bandeiras.
 */
public class Main {
    
    private static final CardValidationService validationService = new CardValidationService();
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            displayWelcomeMessage();
            runInteractiveLoop(scanner);
        } catch (Exception e) {
            System.err.println("❌ Erro crítico na aplicação: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Exibe a mensagem de boas-vindas e instruções.
     */
    private static void displayWelcomeMessage() {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║    🏦 Validador de Bandeiras de Cartão 💳        ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║                                                  ║");
        System.out.println("║  📝 Digite o número do cartão para identificar   ║");
        System.out.println("║     a bandeira e validar o formato               ║");
        System.out.println("║                                                  ║");
        System.out.println("║  🚪 Digite 'sair' para encerrar o programa       ║");
        System.out.println("║  ❓ Digite 'ajuda' para ver bandeiras suportadas ║");
        System.out.println("║                                                  ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.println();
    }
    
    /**
     * Executa o loop principal de interação com o usuário.
     */
    private static void runInteractiveLoop(Scanner scanner) {
        while (true) {
            System.out.print("💳 Número do cartão: ");
            String input = scanner.nextLine().trim();
            
            if (shouldExit(input)) {
                displayGoodbyeMessage();
                break;
            }
            
            if (shouldShowHelp(input)) {
                displayHelp();
                continue;
            }
            
            if (input.isEmpty()) {
                System.out.println("⚠️  Por favor, digite um número de cartão ou 'sair' para encerrar.\n");
                continue;
            }
            
            processCardInput(input);
        }
    }
    
    /**
     * Verifica se o usuário deseja sair da aplicação.
     */
    private static boolean shouldExit(String input) {
        return input.equalsIgnoreCase("sair") || input.equalsIgnoreCase("exit") || 
               input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("q");
    }
    
    /**
     * Verifica se o usuário solicita ajuda.
     */
    private static boolean shouldShowHelp(String input) {
        return input.equalsIgnoreCase("ajuda") || input.equalsIgnoreCase("help") || 
               input.equalsIgnoreCase("h") || input.equals("?");
    }
    
    /**
     * Processa a entrada do cartão e exibe o resultado.
     */
    private static void processCardInput(String input) {
        try {
            CardValidationService.ValidationResult result = validationService.validateAndIdentify(input);
            System.out.println("\n" + result.getMessage() + "\n");
            System.out.println("─".repeat(50));
        } catch (Exception e) {
            System.out.println("❌ Erro inesperado: " + e.getMessage() + "\n");
        }
    }
    
    /**
     * Exibe informações de ajuda sobre as bandeiras suportadas.
     */
    private static void displayHelp() {
        System.out.println("\n🎯 Bandeiras de Cartão Suportadas:");
        System.out.println("─".repeat(50));
        
        CardBrandIdentifier.CardBrand[] brands = CardBrandIdentifier.CardBrand.values();
        for (CardBrandIdentifier.CardBrand brand : brands) {
            if (brand != CardBrandIdentifier.CardBrand.UNKNOWN) {
                System.out.println("  • " + brand.getDisplayName());
            }
        }
        
        System.out.println("\n📏 Requisitos:");
        System.out.println("  • Comprimento: 8 a 19 dígitos");
        System.out.println("  • Formatos aceitos: 1234567890, 1234-5678-9012, 1234 5678 9012");
        System.out.println("  • Validação: Algoritmo de Luhn aplicado");
        System.out.println();
    }
    
    /**
     * Exibe a mensagem de despedida.
     */
    private static void displayGoodbyeMessage() {
        System.out.println();
        System.out.println("👋 Obrigado por usar o Validador de Bandeiras!");
        System.out.println("💡 Desenvolvido com Java - Boas práticas e Clean Code");
        System.out.println("🔒 Seus dados são processados localmente e não são armazenados.");
        System.out.println();
    }
}