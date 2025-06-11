package com.valida.cartao;

public class CardBrandIdentifier {
    public enum CardBrand {
        VISA, MASTERCARD, ELO, AMERICAN_EXPRESS, HIPERCARD, DISCOVER, DINERS_CLUB, JCB, UNIONPAY, UNKNOWN
    }

    public static CardBrand identify(String cardNumber) {
        if (cardNumber == null) return CardBrand.UNKNOWN;
        String sanitized = cardNumber.replaceAll("\\D", "");

        // Visa: começa com 4
        if (sanitized.matches("^4[0-9]{12}(?:[0-9]{3})?$")) return CardBrand.VISA;

        // MasterCard: 51-55, 2221-2720
        if (sanitized.matches("^(5[1-5][0-9]{14}|2(2[2-9][1-9]|2[3-9][0-9]|[3-6][0-9]{2}|7[01][0-9]|720)[0-9]{12})$")) return CardBrand.MASTERCARD;        // Elo: principais BINs conhecidos  
        if (sanitized.matches("^(4011(78|79)|431274|438935|451416|457393|504175|50670[0-9]|50671[0-9]|506720|509[0-9]{3}|627780|636297|636368|650[0-9]{3}|65165[2-9]|6550[0-9]{2})[0-9]*$")) return CardBrand.ELO;

        // American Express: 34 ou 37
        if (sanitized.matches("^3[47][0-9]{13}$")) return CardBrand.AMERICAN_EXPRESS;

        // Hipercard: 606282, 3841 (alguns BINs)
        if (sanitized.matches("^(606282|3841)[0-9]{10,}$")) return CardBrand.HIPERCARD;

        // Discover: 6011, 65, 644-649
        if (sanitized.matches("^(6011|65|64[4-9])[0-9]{12,15}$")) return CardBrand.DISCOVER;

        // Diners Club: 300-305, 36, 38-39
        if (sanitized.matches("^3(0[0-5]|[68][0-9])[0-9]{11,13}$")) return CardBrand.DINERS_CLUB;

        // JCB: 3528-3589
        if (sanitized.matches("^35(2[89]|[3-8][0-9])[0-9]{12}$")) return CardBrand.JCB;

        // UnionPay: 62
        if (sanitized.matches("^62[0-9]{14,17}$")) return CardBrand.UNIONPAY;

        return CardBrand.UNKNOWN;
    }
}
