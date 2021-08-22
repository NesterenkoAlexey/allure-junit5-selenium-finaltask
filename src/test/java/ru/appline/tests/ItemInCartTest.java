package ru.appline.tests;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.appline.base.BaseTest;

import java.util.Locale;


public class ItemInCartTest extends BaseTest {

    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "RUB|" +    //currency
            "300000|" + //depositSum
            "6 месяцев|" + //depositTerm
            "50000|" + //replenishSum
            "ON|" + //monthlyCapitalization
            "12 243,26|" + //percentAmount
            "562 243,26" , //finalAmount

            "USD|" +
            "500000|" +
            "12 месяцев|" +
            "20000|" +
            "ON|" +
            "920,60|" +
            "720 920,60"})
    void test_CvsSourse(String currency,
                        String depositSum,
                        String depositTerm,
                        String replenishSum,
                        String monthlyCapitalization,
                        String percentAmount,
                        String finalAmount) {

        app.getHomePage()
                .selectMenu("Вклады")
                .chooseCurrency(currency)
                .fillSum(depositSum)
                .chooseDepositTerm(depositTerm)
                .fillReplenish(replenishSum)
                .selectMonthlyCapitalization(monthlyCapitalization)
                .percentCheck(percentAmount)
                .finalAmountCheck(finalAmount);


    }

}
