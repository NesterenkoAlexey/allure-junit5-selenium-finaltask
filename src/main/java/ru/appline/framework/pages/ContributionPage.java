package ru.appline.framework.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.text.NumberFormat;
import java.util.Locale;

public class ContributionPage extends BasePage {

    @FindBy(xpath = "//span[text() = 'Рубли']/..")
    private WebElement rubButton;

    @FindBy(xpath = "//span[text() = 'Доллары США']/..")
    private WebElement usdButton;

    @FindBy(xpath = "//input[@name = 'amount']")
    private WebElement amountField;

    @FindBy(xpath = "//input[@name = 'replenish']")
    private WebElement monthlyReplenishField;

    @FindBy(xpath = "//select")
    private WebElement depositTermField;

    //Список срока вклада
    Select depositTermSelector = new Select(depositTermField);

    @FindBy(xpath = "//span[text() = 'Ежемесячная капитализация']//..//..//span[@class = 'calculator__check-block-input']")
    private WebElement monthlyCapitalizationButton;

    @FindBy(xpath = "//span[@class = 'js-calc-amount']")
    private WebElement nowAmountField;

    @FindBy(xpath = "//span[@class = 'js-calc-replenish']")
    private WebElement replenishInNumberOfMonthField;

    @FindBy(xpath = "//span[@class = 'js-calc-earned']")
    private WebElement percentField;

    @FindBy(xpath = "//span[@class = 'js-calc-result']")
    private WebElement finalAmountField;

    @Step("Выбираем валюту - {currencyName}")
    public ContributionPage chooseCurrency(String currencyName) {
        switch (currencyName) {
            case "RUB": {
                rubButton.click();
                break;
            }
            case "USD": {
                usdButton.click();
                break;
            }
            default: {
                Assertions.fail("Нет такой валюты. Есть только RUB и USD");
                break;
            }
        }
        return this;
    }

    @Step("Устанавливаем сумму вклада - {sum}")
    public ContributionPage fillSum(String sum) {

        fillInputField(amountField, sum);

        //ожидание когда сумма появится на страничке
        wait.until(textToBePresentWithoutSpacesInElement(nowAmountField, sum));
        return this;
    }

    @Step("Устанавливаем ежемесячную сумму пополнения вклада - {replenish}")
    public ContributionPage fillReplenish(String replenish) {
        fillInputField(monthlyReplenishField, replenish);

        //Получаем установленное число месяцев
        Integer monthNumber = Integer.parseInt(driverManager.getDriver().findElement(By.xpath("//div[@class = 'jq-selectbox__select-text']"))
                .getText().replaceAll("\\D", "")) - 1;

        //Ожидание, когда отобразится значение пополнения суммы * на выбранное кол-во месяцев
        wait.until(textToBePresentWithoutSpacesInElement(replenishInNumberOfMonthField, Integer.toString(Integer.parseInt(replenish) * monthNumber)));
        return this;
    }
    @Step("Устанавливаем срок вклада - {depositTerm}")
    public ContributionPage chooseDepositTerm(String depositTerm) {

        depositTermSelector.selectByVisibleText(depositTerm);
        Assertions.assertEquals(depositTerm, driverManager.getDriver().findElement(By.xpath("//div[@class = 'jq-selectbox__select-text']")).getText(),
                "Проверка срока вклада");
        return this;
    }

    @Step("Проверяем сумма начисленных процентов - {percentAmount}")
    public ContributionPage percentCheck(String percentAmount) {
        wait.until(textToBePresentWithoutSpacesInElement(percentField, percentAmount.replaceAll("\\D", "")));

        return this;
    }

    @Step("Проверяем конечную сумму - {finalAmount}")
    public ContributionPage finalAmountCheck(String finalAmount) {
        wait.until(textToBePresentWithoutSpacesInElement(finalAmountField, finalAmount.replaceAll("\\D", "")));

        return this;
    }

    @Step("Выставляем поле Ежемесячной капитализации в статус - {value}")
    public ContributionPage selectMonthlyCapitalization(String value) {
        switch (value) {
            case ("ON"): {
                if (isElementPresent(By.xpath("//span[text() = 'Ежемесячная капитализация']//..//..//div[@class = 'jq-checkbox calculator__check'] "))) {
                    monthlyCapitalizationButton.click();
                }
                break;
            }
            case ("OFF"): {
                if (isElementPresent(By.xpath("//span[text() = 'Ежемесячная капитализация']//..//..//div[@class = 'jq-checkbox calculator__check checked']"))) {
                    monthlyCapitalizationButton.click();
                }
                break;
            }
            default: {
                Assertions.fail();
            }
        }
        return this;
    }

}
