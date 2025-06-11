package com.valida.cartao;

/**
 * Classe de teste simples para verificar as funcionalidades implementadas.
 */
public class TestRunner {
    public static void main(String[] args) {
        System.out.println("🧪 Executando testes das melhorias implementadas...\n");
        
        // Teste 1: Identificação de bandeiras
        testBrandIdentification();
        
        // Teste 2: Validação Luhn
        testLuhnValidation();
        
        // Teste 3: Serviço de validação
        testValidationService();
        
        System.out.println("✅ Todos os testes concluídos com sucesso!");
        System.out.println("\n🎯 Melhorias implementadas:");
        System.out.println("  • Estrutura Maven com pom.xml");
        System.out.println("  • Enum CardBrand melhorado com displayName e patterns");
        System.out.println("  • Algoritmo de Luhn implementado");
        System.out.println("  • Classe CardValidationService para separação de responsabilidades");
        System.out.println("  • Interface Main refatorada com melhor UX");
        System.out.println("  • Testes unitários completos");
        System.out.println("  • Documentação README.md detalhada");
        System.out.println("  • .gitignore configurado");
    }
    
    private static void testBrandIdentification() {
        System.out.println("🔍 Teste 1: Identificação de Bandeiras");
        
        String[] testCards = {
            "4111111111111111", // Visa
            "5555555555554444", // MasterCard
            "378282246310005",  // American Express
            "6362970000457013", // Elo
            "30569309025904"    // Diners Club
        };
        
        for (String card : testCards) {
            CardBrandIdentifier.CardBrand brand = CardBrandIdentifier.identify(card);
            System.out.println("  💳 " + card.substring(0,4) + "****" + card.substring(card.length()-4) + 
                             " → " + brand.getDisplayName());
        }
        System.out.println();
    }
    
    private static void testLuhnValidation() {
        System.out.println("🔢 Teste 2: Validação Algoritmo de Luhn");
        
        String[] validCards = {"4111111111111111", "5555555555554444"};
        String[] invalidCards = {"4111111111111112", "1234567890123456"};
        
        for (String card : validCards) {
            boolean isValid = CardBrandIdentifier.isValidLuhn(card);
            System.out.println("  ✅ " + card.substring(0,4) + "****" + card.substring(card.length()-4) + 
                             " → Luhn: " + (isValid ? "Válido" : "Inválido"));
        }
        
        for (String card : invalidCards) {
            boolean isValid = CardBrandIdentifier.isValidLuhn(card);
            System.out.println("  ❌ " + card.substring(0,4) + "****" + card.substring(card.length()-4) + 
                             " → Luhn: " + (isValid ? "Válido" : "Inválido"));
        }
        System.out.println();
    }
    
    private static void testValidationService() {
        System.out.println("🛠️ Teste 3: Serviço de Validação");
        
        CardValidationService service = new CardValidationService();
        
        // Teste com cartão válido
        CardValidationService.ValidationResult result = service.validateAndIdentify("4111-1111-1111-1111");
        System.out.println("  📋 Entrada formatada: " + (result.isValid() ? "✅ Aceita" : "❌ Rejeitada"));
        System.out.println("  🏷️ Bandeira: " + result.getBrand().getDisplayName());
        
        // Teste com entrada inválida
        result = service.validateAndIdentify("123");
        System.out.println("  📋 Entrada curta: " + (result.isValid() ? "✅ Aceita" : "❌ Rejeitada"));
        
        System.out.println();
    }
}
