package ui.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit.ScreenShooter;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.qameta.allure.selenide.LogType;
import org.junit.BeforeClass;
import org.junit.Rule;
import util.ConfigHandler;

import java.util.logging.Level;

public class BaseUITest {
    static ConfigHandler config = new ConfigHandler();
    @Rule
    public ScreenShooter makeScreenshotonFailedTests = ScreenShooter.failedTests().succeededTests();


    @BeforeClass
    public static void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
                .includeSelenideSteps(true)
                .enableLogs(LogType.CLIENT, Level.ALL)
        );
        Configuration.reportsFolder = config.getProperty("screenshots_path");
        Configuration.baseUrl = config.getProperty("ui_base_uri");
    }
}
