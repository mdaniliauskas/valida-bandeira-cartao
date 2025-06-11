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
        if (sanitized.matches("^(5[1-5][0-9]{14}|2(2[2-9][1-9]|2[3-9][0-9]|[3-6][0-9]{2}|7[01][0-9]|720)[0-9]{12})$")) return CardBrand.MASTERCARD;

        // Elo: principais BINs conhecidos
        if (sanitized.matches("^(4011(78|79)|431274|438935|451416|457393|504175|5067(0[0-9]|1[0-9]|20)|5090(4[0-9]|5[0-9]|6[0-9]|7[0-9]|8[0-9]|9[0-9])|627780|636297|636368|650(031|033|035|051|405|439|485|504|505|506|508|509|511|512|513|514|515|516|517|518|519|520|521|522|523|524|525|526|527|528|529|530|531|532|533|534|535|536|537|538|539|541|542|543|544|545|546|547|548|549|550|551|552|553|554|555|556|557|558|559|560|561|562|563|564|565|566|567|568|569|570|571|572|573|574|575|576|577|578|579|580|581|582|583|584|585|586|587|588|589|590|591|592|593|594|595|596|597|598|599)|6516(52|53|54|55|56|57|58|59)|6550(00|01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99)))[0-9]*$")) return CardBrand.ELO;

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
