package demo;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class GoogleSearchTests {
    @Rule
    public ScreenShooter makeScreenshotOnFailure = ScreenShooter.failedTests().succeededTests();

    @Before
    public void setup(){
        Configuration.browser = "firefox";
        Configuration.reportsFolder = "/Users/renju/Devworx/intellij-workspace/TestFramework/src/main/resources/reports";
        Configuration.baseUrl = "https://bing.com";
    }

    @Test
    public void search() {
        GoogleSearchPage searchPage = Selenide.open("/login", GoogleSearchPage.class);
        GoogleResultsPage resultsPage = searchPage.search("selenide");
        resultsPage.results().shouldHave(CollectionCondition.size(10));
        resultsPage.results().get(0).shouldHave(Condition.text("Selenide: Concise UI Tests in Java"));
    }
}
