package demo.ui.tests;

import java.util.logging.Level;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.selenide.AllureSelenide;
import io.qameta.allure.selenide.LogType;
import demo.util.ConfigHandler;
import org.testng.annotations.BeforeClass;

public class BaseUITest {
    static ConfigHandler config = ConfigHandler.getInstance();

    @BeforeClass
    public void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
                .includeSelenideSteps(true)
                .enableLogs(LogType.CLIENT, Level.ALL)
        );
        Configuration.reportsFolder = config.getProperty("screenshots_path");
        Configuration.baseUrl = config.getProperty("ui_base_uri");
        ScreenShooter.captureSuccessfulTests = true;
    }
}
