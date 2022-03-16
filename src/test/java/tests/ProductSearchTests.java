package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.junit.ScreenShooter;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.impl.SimpleLoggerFactory;
import pageobjects.ContactUsPage;
import pageobjects.LandingPage;

import java.io.File;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;

public class ProductSearchTests {
    Logger logger = new SimpleLoggerFactory().getLogger("ProductSearchTests");
    //TODO Make path relative
    String sampleUploadFilePath = "/Users/renju/Devworx/intellij-workspace/TestFramework/src/main/resources/assets/testimg.png";
    //TODO move screenshots to date folder
    @Rule
    public ScreenShooter makeScreenshotAlways = ScreenShooter.failedTests().succeededTests();


    //TODO Move to baseclass
    @Before
    public void setup(){
        logger.debug("Test Setup started");
//        Configuration.browser = "firefox";
        Configuration.reportsFolder = "/Users/renju/Devworx/intellij-workspace/TestFramework/src/main/resources/reports";
        Configuration.baseUrl = "https://automationpractice.multiformis.com";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        logger.debug("Test Setup over");
    }

    @Test
    public void searchProduct(){
        LandingPage landingPage = open("/index.php", LandingPage.class);
        ElementsCollection results = landingPage.search("blouse");
        results.shouldHave(size(1));
    }

    @Test
    public void contactSeller(){
        File file = new File(sampleUploadFilePath);
        ContactUsPage contactUsPage = open("index.php?controller=contact", ContactUsPage.class);
        contactUsPage.selectSubjectHeading("2")
                .enterEmail("test@test.com")
                .enterOrderReference("C123")
                .uploadFile(file)
                .enterMessage("Sample feedback text message")
                .submitForm();
    }
}
