package ui.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.BeforeClass;
import org.junit.Test;
import ui.pageobjects.ContactUsPage;
import ui.pageobjects.LandingPage;

import java.io.File;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;

public class ProductSearchTests {
//    Logger logger = new SimpleLoggerFactory().getLogger("ProductSearchTests");
    //TODO Make path relative
    String sampleUploadFilePath = "/Users/renju/Devworx/intellij-workspace/TestFramework/assets/testimg.png";
    //TODO move screenshots to date folder
//    @Rule
//    public ScreenShooter makeScreenshotonFailedTests = ScreenShooter.failedTests();

    //TODO Move to baseclass
    @BeforeClass
    public static void preTestSetup(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.reportsFolder = "/Users/renju/Devworx/intellij-workspace/TestFramework/reports";
        Configuration.baseUrl = "https://automationpractice.multiformis.com";
    }



//    @Before
    public void setup(){
//        logger.debug("Test Setup started");
//        Configuration.browser = "firefox";
//        logger.debug("Test Setup over");
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
        ContactUsPage contactUsPage = open("/index.php?controller=contact", ContactUsPage.class);
        contactUsPage.selectSubjectHeading("2")
                .enterEmail("test@test.com")
                .enterOrderReference("C123")
                .uploadFile(file)
                .enterMessage("Sample feedback text message")
                .submitForm();
    }
}
