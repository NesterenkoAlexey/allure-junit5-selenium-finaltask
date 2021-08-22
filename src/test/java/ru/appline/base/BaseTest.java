package ru.appline.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.appline.framework.managers.DriverManager;
import ru.appline.framework.managers.InitManager;
import ru.appline.framework.managers.PageManager;
import ru.appline.framework.managers.TestPropManager;
import ru.appline.framework.utils.MyAllureListener;

import static ru.appline.framework.utils.PropConst.BASE_URL;


@ExtendWith(MyAllureListener.class)
public class BaseTest {

     // Менеджер страничек
    protected PageManager app = PageManager.getPageManager();
    private static final TestPropManager props = TestPropManager.getTestPropManager();

    // Менеджер WebDriver
    private final DriverManager driverManager = DriverManager.getDriverManager();

    @BeforeAll
    public static void beforeAll() {
        InitManager.initFramework();
    }

    @BeforeEach
    public void beforeEach() {
        driverManager.getDriver().get(props.getProperty(BASE_URL));
    }

    @AfterAll
    public static void afterAll() {
        InitManager.quitFramework();
    }
}
