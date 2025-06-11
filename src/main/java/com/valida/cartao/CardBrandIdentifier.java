package com.valida.cartao;

import java.util.regex.Pattern;

/**
 * Identificador de bandeiras de cartão de crédito.
 * 
 * Esta classe fornece funcionalidade para identificar a bandeira de um cartão
 * com base no seu número, usando padrões específicos de cada bandeira.
 */
public class CardBrandIdentifier {
    
    /**
     * Enumeração das bandeiras de cartão suportadas.
     */
    public enum CardBrand {
        VISA("Visa", "^4[0-9]{12}(?:[0-9]{3})?$"),
        MASTERCARD("MasterCard", "^(5[1-5][0-9]{14}|2(2[2-9][1-9]|2[3-9][0-9]|[3-6][0-9]{2}|7[01][0-9]|720)[0-9]{12})$"),
        ELO("Elo", "^(4011(78|79)|431274|438935|451416|457393|504175|50670[0-9]|50671[0-9]|506720|509[0-9]{3}|627780|636297|636368|650[0-9]{3}|65165[2-9]|6550[0-9]{2})[0-9]*$"),
        AMERICAN_EXPRESS("American Express", "^3[47][0-9]{13}$"),
        HIPERCARD("Hipercard", "^(606282|3841)[0-9]{10,}$"),
        DISCOVER("Discover", "^(6011|65|64[4-9])[0-9]{12,15}$"),
        DINERS_CLUB("Diners Club", "^3(0[0-5]|[68][0-9])[0-9]{11,13}$"),
        JCB("JCB", "^35(2[89]|[3-8][0-9])[0-9]{12}$"),
        UNIONPAY("UnionPay", "^62[0-9]{14,17}$"),
        UNKNOWN("Bandeira Desconhecida", null);
        
        private final String displayName;
        private final Pattern pattern;
        
        CardBrand(String displayName, String regex) {
            this.displayName = displayName;
            this.pattern = regex != null ? Pattern.compile(regex) : null;
        }
        
        /**
         * Retorna o nome de exibição da bandeira.
         */
        public String getDisplayName() {
            return displayName;
        }
        
        /**
         * Verifica se o número do cartão corresponde ao padrão desta bandeira.
         */
        public boolean matches(String cardNumber) {
            return pattern != null && pattern.matcher(cardNumber).matches();
        }
        
        @Override
        public String toString() {
            return displayName;
        }
    }    /**
     * Identifica a bandeira do cartão com base no número fornecido.
     * 
     * @param cardNumber o número do cartão (pode conter espaços, hífens, etc.)
     * @return a bandeira identificada ou UNKNOWN se não for reconhecida
     */
    public static CardBrand identify(String cardNumber) {
        if (cardNumber == null || cardNumber.trim().isEmpty()) {
            return CardBrand.UNKNOWN;
        }
        
        String sanitized = sanitizeCardNumber(cardNumber);
        
        if (!isValidLength(sanitized)) {
            return CardBrand.UNKNOWN;
        }
        
        // Tenta identificar a bandeira testando cada padrão
        for (CardBrand brand : CardBrand.values()) {
            if (brand != CardBrand.UNKNOWN && brand.matches(sanitized)) {
                return brand;
            }
        }
        
        return CardBrand.UNKNOWN;
    }
    
    /**
     * Remove todos os caracteres não numéricos do número do cartão.
     */
    private static String sanitizeCardNumber(String cardNumber) {
        return cardNumber.replaceAll("\\D", "");
    }
    
    /**
     * Verifica se o comprimento do número está dentro dos limites válidos.
     */
    private static boolean isValidLength(String cardNumber) {
        int length = cardNumber.length();
        return length >= 8 && length <= 19 && !cardNumber.matches("^0+$");
    }
    
    /**
     * Valida um número de cartão usando o algoritmo de Luhn.
     * 
     * @param cardNumber o número do cartão a ser validado
     * @return true se o número for válido segundo o algoritmo de Luhn
     */
    public static boolean isValidLuhn(String cardNumber) {
        if (cardNumber == null) return false;
        
        String sanitized = sanitizeCardNumber(cardNumber);
        if (sanitized.length() < 2) return false;
        
        int sum = 0;
        boolean alternate = false;
        
        // Percorre os dígitos de trás para frente
        for (int i = sanitized.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(sanitized.charAt(i));
            
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit = (digit % 10) + 1;
                }
            }
            
            sum += digit;
            alternate = !alternate;
        }
        
        return sum % 10 == 0;
    }
}
