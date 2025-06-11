package com.valida.cartao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe CardBrandIdentifier.
 */
@DisplayName("Testes do Identificador de Bandeiras de Cartão")
class CardBrandIdentifierTest {
    
    @Test
    @DisplayName("Deve identificar corretamente cartões Visa")
    void testVisaIdentification() {
        // Visa 13 dígitos
        assertEquals(CardBrandIdentifier.CardBrand.VISA, 
            CardBrandIdentifier.identify("4111111111111"));
            
        // Visa 16 dígitos
        assertEquals(CardBrandIdentifier.CardBrand.VISA, 
            CardBrandIdentifier.identify("4111111111111111"));
            
        // Visa com espaços
        assertEquals(CardBrandIdentifier.CardBrand.VISA, 
            CardBrandIdentifier.identify("4111 1111 1111 1111"));
    }
    
    @Test
    @DisplayName("Deve identificar corretamente cartões MasterCard")
    void testMasterCardIdentification() {
        // MasterCard série 5
        assertEquals(CardBrandIdentifier.CardBrand.MASTERCARD, 
            CardBrandIdentifier.identify("5555555555554444"));
            
        // MasterCard série 2
        assertEquals(CardBrandIdentifier.CardBrand.MASTERCARD, 
            CardBrandIdentifier.identify("2223000048400011"));
    }
    
    @Test
    @DisplayName("Deve identificar corretamente cartões American Express")
    void testAmericanExpressIdentification() {
        assertEquals(CardBrandIdentifier.CardBrand.AMERICAN_EXPRESS, 
            CardBrandIdentifier.identify("378282246310005"));
            
        assertEquals(CardBrandIdentifier.CardBrand.AMERICAN_EXPRESS, 
            CardBrandIdentifier.identify("371449635398431"));
    }
    
    @Test
    @DisplayName("Deve identificar corretamente cartões Elo")
    void testEloIdentification() {
        assertEquals(CardBrandIdentifier.CardBrand.ELO, 
            CardBrandIdentifier.identify("6362970000457013"));
            
        assertEquals(CardBrandIdentifier.CardBrand.ELO, 
            CardBrandIdentifier.identify("5067206500000000"));
    }
    
    @Test
    @DisplayName("Deve identificar corretamente cartões Diners Club")
    void testDinersClubIdentification() {
        assertEquals(CardBrandIdentifier.CardBrand.DINERS_CLUB, 
            CardBrandIdentifier.identify("30569309025904"));
            
        assertEquals(CardBrandIdentifier.CardBrand.DINERS_CLUB, 
            CardBrandIdentifier.identify("36000000000000"));
    }
    
    @Test
    @DisplayName("Deve identificar corretamente cartões JCB")
    void testJCBIdentification() {
        assertEquals(CardBrandIdentifier.CardBrand.JCB, 
            CardBrandIdentifier.identify("3530111333300000"));
    }
    
    @Test
    @DisplayName("Deve identificar corretamente cartões Discover")
    void testDiscoverIdentification() {
        assertEquals(CardBrandIdentifier.CardBrand.DISCOVER, 
            CardBrandIdentifier.identify("6011111111111117"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "abc", "0000000000", "123"})
    @DisplayName("Deve retornar UNKNOWN para entradas inválidas")
    void testInvalidInputs(String input) {
        assertEquals(CardBrandIdentifier.CardBrand.UNKNOWN, 
            CardBrandIdentifier.identify(input));
    }
    
    @Test
    @DisplayName("Deve retornar UNKNOWN para entrada null")
    void testNullInput() {
        assertEquals(CardBrandIdentifier.CardBrand.UNKNOWN, 
            CardBrandIdentifier.identify(null));
    }
    
    @Test
    @DisplayName("Deve validar corretamente o algoritmo de Luhn")
    void testLuhnValidation() {
        // Números válidos segundo Luhn
        assertTrue(CardBrandIdentifier.isValidLuhn("4111111111111111")); // Visa válido
        assertTrue(CardBrandIdentifier.isValidLuhn("5555555555554444")); // MasterCard válido
        assertTrue(CardBrandIdentifier.isValidLuhn("378282246310005"));  // AmEx válido
        
        // Números inválidos segundo Luhn
        assertFalse(CardBrandIdentifier.isValidLuhn("4111111111111112")); // Dígito errado
        assertFalse(CardBrandIdentifier.isValidLuhn("1234567890123456")); // Sequência simples
        
        // Casos extremos
        assertFalse(CardBrandIdentifier.isValidLuhn(null));
        assertFalse(CardBrandIdentifier.isValidLuhn(""));
        assertFalse(CardBrandIdentifier.isValidLuhn("1"));
    }
    
    @Test
    @DisplayName("Deve sanitizar corretamente números com formatação")
    void testFormattedNumbers() {
        // Números com hífens
        assertEquals(CardBrandIdentifier.CardBrand.VISA, 
            CardBrandIdentifier.identify("4111-1111-1111-1111"));
            
        // Números com espaços múltiplos
        assertEquals(CardBrandIdentifier.CardBrand.VISA, 
            CardBrandIdentifier.identify("  4111   1111   1111   1111  "));
            
        // Números com caracteres especiais
        assertEquals(CardBrandIdentifier.CardBrand.VISA, 
            CardBrandIdentifier.identify("4111.1111.1111.1111"));
    }
    
    @Test
    @DisplayName("Deve validar getDisplayName do enum")
    void testCardBrandDisplayNames() {
        assertEquals("Visa", CardBrandIdentifier.CardBrand.VISA.getDisplayName());
        assertEquals("MasterCard", CardBrandIdentifier.CardBrand.MASTERCARD.getDisplayName());
        assertEquals("American Express", CardBrandIdentifier.CardBrand.AMERICAN_EXPRESS.getDisplayName());
        assertEquals("Bandeira Desconhecida", CardBrandIdentifier.CardBrand.UNKNOWN.getDisplayName());
    }
}
