package com.valida.cartao;

/**
 * Serviço responsável pela validação e identificação de cartões de crédito.
 * 
 * Esta classe centraliza a lógica de negócio relacionada à validação
 * e identificação de bandeiras de cartão de crédito.
 */
public class CardValidationService {
    
    /**
     * Resultado da validação e identificação de um cartão.
     */
    public static class ValidationResult {
        private final boolean isValid;
        private final CardBrandIdentifier.CardBrand brand;
        private final String message;
        private final String formattedNumber;
        
        public ValidationResult(boolean isValid, CardBrandIdentifier.CardBrand brand, 
                              String message, String formattedNumber) {
            this.isValid = isValid;
            this.brand = brand;
            this.message = message;
            this.formattedNumber = formattedNumber;
        }
        
        public boolean isValid() { return isValid; }
        public CardBrandIdentifier.CardBrand getBrand() { return brand; }
        public String getMessage() { return message; }
        public String getFormattedNumber() { return formattedNumber; }
    }
    
    /**
     * Valida e identifica a bandeira de um cartão de crédito.
     * 
     * @param input a entrada do usuário contendo o número do cartão
     * @return resultado da validação contendo informações sobre o cartão
     */
    public ValidationResult validateAndIdentify(String input) {
        // Verificar entrada vazia
        if (input == null || input.trim().isEmpty()) {
            return new ValidationResult(false, CardBrandIdentifier.CardBrand.UNKNOWN,
                "❌ Entrada inválida: Por favor, digite um número de cartão.", "");
        }
        
        // Sanitizar entrada
        String sanitized = input.replaceAll("\\D", "");
        
        // Verificar se sobrou algum dígito após sanitização
        if (sanitized.isEmpty()) {
            return new ValidationResult(false, CardBrandIdentifier.CardBrand.UNKNOWN,
                "❌ Entrada inválida: Não foram encontrados números na entrada '" + input + "'", "");
        }
        
        // Verificar comprimento
        if (sanitized.length() < 8) {
            return new ValidationResult(false, CardBrandIdentifier.CardBrand.UNKNOWN,
                "❌ Número muito curto: " + sanitized.length() + " dígitos. Cartões válidos têm entre 8 e 19 dígitos.", "");
        }
        
        if (sanitized.length() > 19) {
            return new ValidationResult(false, CardBrandIdentifier.CardBrand.UNKNOWN,
                "❌ Número muito longo: " + sanitized.length() + " dígitos. Cartões válidos têm entre 8 e 19 dígitos.", "");
        }
        
        // Verificar se contém apenas zeros
        if (sanitized.matches("^0+$")) {
            return new ValidationResult(false, CardBrandIdentifier.CardBrand.UNKNOWN,
                "❌ Número inválido: Não pode conter apenas zeros.", "");
        }
        
        // Identificar bandeira
        CardBrandIdentifier.CardBrand brand = CardBrandIdentifier.identify(sanitized);
        String formattedNumber = formatCardNumber(sanitized);
        
        // Verificar algoritmo de Luhn
        boolean isLuhnValid = CardBrandIdentifier.isValidLuhn(sanitized);
        String luhnStatus = isLuhnValid ? "✅ Válido" : "⚠️ Inválido";
        
        if (brand == CardBrandIdentifier.CardBrand.UNKNOWN) {
            return new ValidationResult(false, brand,
                "❓ Bandeira não identificada para o número: " + formattedNumber + 
                "\n   📏 Comprimento: " + sanitized.length() + " dígitos" +
                "\n   🔍 Algoritmo de Luhn: " + luhnStatus +
                "\n   ℹ️  O número não corresponde aos padrões conhecidos.", formattedNumber);
        }
        
        return new ValidationResult(true, brand,
            "✅ Bandeira identificada: " + brand + 
            "\n   💳 Número: " + formattedNumber +
            "\n   📏 Comprimento: " + sanitized.length() + " dígitos" +
            "\n   🔍 Algoritmo de Luhn: " + luhnStatus, formattedNumber);
    }
    
    /**
     * Formata o número do cartão para exibição, mascarando os dígitos do meio.
     * 
     * @param cardNumber o número do cartão sanitizado
     * @return o número formatado com mascaramento de segurança
     */
    private String formatCardNumber(String cardNumber) {
        if (cardNumber.length() <= 8) {
            return cardNumber.substring(0, 4) + " **** " + cardNumber.substring(cardNumber.length() - 4);
        }
        return cardNumber.substring(0, 4) + " **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }
}
