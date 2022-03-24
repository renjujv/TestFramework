package demo.ui.tests;

import com.codeborne.selenide.Condition;
import demo.ui.pageobjects.ContactUsPage;
import demo.ui.pageobjects.LandingPage;
import demo.ui.pageobjects.ProductPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;

public class EcomWebsiteTests extends BaseUITest{
    private final String PRODUCT_TITLE = "div.product-container > div.right-block > h5 > a";

    @Test
    @Description("Search product `t-shirt` and verify that the result count is `1`")
    @Severity(SeverityLevel.BLOCKER)
    public void searchProductAndVerifyResults(){
        LandingPage landingPage = open("/index.php", LandingPage.class);
        landingPage
                .performSearchAndGiveResults("t-shirt")
                .shouldHave(size(1));
    }

    @Test
    @Description("Search product `t-shirt` and verify that the result contains the searched text")
    @Severity(SeverityLevel.BLOCKER)
    public void searchProductAndVerifyResultText(){
        LandingPage landingPage = open("/index.php", LandingPage.class);
        landingPage
                .performSearchAndGiveResults("t-shirt")
                .first().find(PRODUCT_TITLE).shouldHave(Condition.text("t-shirt"));
    }

    @Test
    @Description("Contact the seller using the `Contact Us` form and verify feedback has been sent.")
    @Severity(SeverityLevel.NORMAL)
    public void contactSellerAndVerifySuccessMessagePresent(){
        ContactUsPage contactUsPage = open("/index.php?controller=contact", ContactUsPage.class);
        contactUsPage
                .contactSeller()
                .verifySuccessMessageIsPresent();
    }

    @Test
    @Description("Search for a Product, add it to Cart and verify it is added to Cart successfully.")
    @Severity(SeverityLevel.CRITICAL)
    public void addtoCartAndVerifyAddition(){
        LandingPage landingPage = open("/index.php", LandingPage.class);
        ProductPage productPage = landingPage.performSearch("t-shirt").viewProductDetails();
        productPage.changeSize("M")
                .verifyAvailabilityStatus("In Stock")
                .addToCart()
                .verifySuccessMessageIsPresent();
    }
}
