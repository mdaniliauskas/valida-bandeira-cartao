package com.valida.cartao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe CardValidationService.
 */
@DisplayName("Testes do Serviço de Validação de Cartão")
class CardValidationServiceTest {
    
    private CardValidationService service;
    
    @BeforeEach
    void setUp() {
        service = new CardValidationService();
    }
    
    @Test
    @DisplayName("Deve validar com sucesso um cartão Visa válido")
    void testValidVisaCard() {
        CardValidationService.ValidationResult result = 
            service.validateAndIdentify("4111111111111111");
        
        assertTrue(result.isValid());
        assertEquals(CardBrandIdentifier.CardBrand.VISA, result.getBrand());
        assertTrue(result.getMessage().contains("✅ Bandeira identificada: Visa"));
        assertEquals("4111 **** **** 1111", result.getFormattedNumber());
    }
    
    @Test
    @DisplayName("Deve rejeitar entrada vazia")
    void testEmptyInput() {
        CardValidationService.ValidationResult result = 
            service.validateAndIdentify("");
        
        assertFalse(result.isValid());
        assertEquals(CardBrandIdentifier.CardBrand.UNKNOWN, result.getBrand());
        assertTrue(result.getMessage().contains("❌ Entrada inválida"));
    }
    
    @Test
    @DisplayName("Deve rejeitar entrada null")
    void testNullInput() {
        CardValidationService.ValidationResult result = 
            service.validateAndIdentify(null);
        
        assertFalse(result.isValid());
        assertEquals(CardBrandIdentifier.CardBrand.UNKNOWN, result.getBrand());
    }
    
    @Test
    @DisplayName("Deve rejeitar entrada sem números")
    void testNoNumbersInput() {
        CardValidationService.ValidationResult result = 
            service.validateAndIdentify("abcdef");
        
        assertFalse(result.isValid());
        assertTrue(result.getMessage().contains("Não foram encontrados números"));
    }
    
    @Test
    @DisplayName("Deve rejeitar números muito curtos")
    void testTooShortNumber() {
        CardValidationService.ValidationResult result = 
            service.validateAndIdentify("1234567");
        
        assertFalse(result.isValid());
        assertTrue(result.getMessage().contains("muito curto"));
    }
    
    @Test
    @DisplayName("Deve rejeitar números muito longos")
    void testTooLongNumber() {
        CardValidationService.ValidationResult result = 
            service.validateAndIdentify("12345678901234567890");
        
        assertFalse(result.isValid());
        assertTrue(result.getMessage().contains("muito longo"));
    }
    
    @Test
    @DisplayName("Deve rejeitar números contendo apenas zeros")
    void testAllZerosNumber() {
        CardValidationService.ValidationResult result = 
            service.validateAndIdentify("0000000000000000");
        
        assertFalse(result.isValid());
        assertTrue(result.getMessage().contains("apenas zeros"));
    }
    
    @Test
    @DisplayName("Deve formatar corretamente números curtos")
    void testShortNumberFormatting() {
        CardValidationService.ValidationResult result = 
            service.validateAndIdentify("12345678");
        
        // Mesmo sendo inválido por não corresponder a nenhuma bandeira,
        // deve formatar corretamente
        assertTrue(result.getFormattedNumber().equals("1234 **** 5678") || 
                  result.getFormattedNumber().isEmpty());
    }
    
    @Test
    @DisplayName("Deve incluir informações do algoritmo de Luhn")
    void testLuhnInformation() {
        CardValidationService.ValidationResult result = 
            service.validateAndIdentify("4111111111111111");
        
        assertTrue(result.getMessage().contains("Algoritmo de Luhn"));
        assertTrue(result.getMessage().contains("✅ Válido") || 
                  result.getMessage().contains("⚠️ Inválido"));
    }
    
    @Test
    @DisplayName("Deve processar números com formatação")
    void testFormattedInput() {
        CardValidationService.ValidationResult result = 
            service.validateAndIdentify("4111-1111-1111-1111");
        
        assertTrue(result.isValid());
        assertEquals(CardBrandIdentifier.CardBrand.VISA, result.getBrand());
    }
}
