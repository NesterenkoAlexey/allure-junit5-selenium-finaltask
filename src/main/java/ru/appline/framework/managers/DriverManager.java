package ru.appline.framework.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static ru.appline.framework.utils.PropConst.PATH_CHROME_DRIVER_WINDOWS;


public class DriverManager {

    /**
     * Переменна для хранения объекта веб-драйвера
     *
     * @see WebDriver
     */
    private WebDriver driver;


    /**
     * Переменна для хранения объекта DriverManager
     */
    private static DriverManager INSTANCE = null;

    private DriverManager() {
    }
    public static DriverManager getDriverManager() {
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return INSTANCE;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver" , "src/main/resources/chromedriver.exe");
            driver = new ChromeDriver();
        }
        return driver;
    }


    // Закрытие драйвера
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }




}
