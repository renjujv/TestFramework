package demo.ui.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.testng.ScreenShooter;
import com.codeborne.selenide.testng.TextReport;
import demo.ui.pageobjects.ContactUsPage;
import demo.ui.pageobjects.LandingPage;
import demo.ui.pageobjects.ProductPage;
import demo.ui.pageobjects.SearchResultsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;

@Listeners({ ScreenShooter.class, TextReport.class})
public class EcomWebsiteTests extends BaseUITest{

    @Test() @Severity(SeverityLevel.BLOCKER)
    @Description("Search product `t-shirt` and verify that the result contains the searched text")
    public void test001_searchProductAndVerifyResultText(){
        LandingPage landingPage = open("/index.php", LandingPage.class);
        SearchResultsPage resultsPage = landingPage.performSearch("t-shirt");
        resultsPage.getProductNames().first().shouldHave(Condition.text("t-shirt"));
    }

    @Test() @Severity(SeverityLevel.BLOCKER)
    @Description("Search product `t-shirt` and verify that the result count is `1`")
    public void test002_searchProductAndVerifyResults(){
        LandingPage landingPage = open("/index.php", LandingPage.class);
        landingPage
                .performSearch("t-shirt")
                .getResults()
                .shouldHave(size(1));
    }

    @Test(groups = {"data-gen"}) @Severity(SeverityLevel.CRITICAL)
    @Description("Search for a Product, add it to Cart and verify it is added to Cart successfully.")
    public void test003_addtoCartAndVerifyAddition(){
        LandingPage landingPage = open("/index.php", LandingPage.class);
        ProductPage productPage = landingPage.performSearch("t-shirt").viewProductDetails();
        productPage.changeSize("M")
                .addToCart()
                .verifySuccessMessageIsPresent();
    }

    @Test() @Severity(SeverityLevel.NORMAL)
    @Description("Contact the seller using the `Contact Us` form and verify feedback has been sent.")
    public void test004_contactSellerAndVerifySuccessMessagePresent(){
        ContactUsPage contactUsPage = open("/index.php?controller=contact", ContactUsPage.class);
        contactUsPage
                .contactSeller()
                .verifySuccessMessageIsPresent();
    }
}
