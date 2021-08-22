package ru.appline.framework.pages;


import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage{

    //Список меню
    @FindBy (xpath = "//div[@class = 'service']")
    private List<WebElement> listMenuBlock;

    //Выбор меню из блока
    @Step("Выбираем меню {nameMenu}")
    public ContributionPage selectMenu(String nameMenu) {
        for (WebElement menuItem : listMenuBlock) {
            if (menuItem.findElement(By.xpath(".//div[@class = 'service__title-text']")).getText().equalsIgnoreCase(nameMenu)) {
                waitUtilElementToBeClickable(menuItem.findElement(By.xpath(".//div[@class = 'service__title-text']/../a[@class = 'service__title-action']"))).click();

                return pageManager.getContributionPage();
            }
        }
        Assertions.fail("Меню'" + nameMenu + "' не было найдено на стартовой странице!");
        return pageManager.getContributionPage();
    }
}
