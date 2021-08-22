package ru.appline.framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.appline.framework.managers.DriverManager;
import ru.appline.framework.managers.PageManager;
import ru.appline.framework.managers.TestPropManager;

import java.util.concurrent.TimeUnit;

import static ru.appline.framework.utils.PropConst.IMPLICITLY_WAIT;

public class BasePage {


    private static final TestPropManager props = TestPropManager.getTestPropManager();
    protected final DriverManager driverManager = DriverManager.getDriverManager();


    protected PageManager pageManager = PageManager.getPageManager();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 15, 1000);
    protected JavascriptExecutor js = (JavascriptExecutor) driverManager.getDriver();

    //Кнопка для возвращиния на стартовую страницу
    @FindBy(xpath = "//a[@class = 'logo']")
    private WebElement logoButton;

    public BasePage() {

        PageFactory.initElements(driverManager.getDriver(), this);

    }

    // Скролл до любого эл-та
    protected WebElement scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }



    //Явное ожидание состояния clickable элемента
    protected WebElement waitUtilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    //Заполнение поля
    protected void fillInputField(WebElement field, String value) {

        field.clear();
        field.sendKeys(value);
    }

    //Проверка существует ли элемент
    public boolean isElementPresent(By by) {
        boolean flag = false;
        try {
            driverManager.getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            driverManager.getDriver().findElement(by);
            flag = true;
        } catch (NoSuchElementException ignore) {

        } finally {
            driverManager.getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
        }
        return flag;
    }

    public static ExpectedCondition<Boolean> textToBePresentWithoutSpacesInElement(final WebElement element, final String text) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    String elementText = element.getText().replaceAll("\\D" , "");
                    return elementText.contains(text);
                } catch (StaleElementReferenceException var3) {
                    return null;
                }
            }

            public String toString() {
                return String.format("text ('%s') to be present in element %s", text, element);
            }
        };
    }

}
