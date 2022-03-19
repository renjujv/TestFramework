package ui.tests;

import com.codeborne.selenide.ElementsCollection;
import org.junit.Test;
import ui.pageobjects.ContactUsPage;
import ui.pageobjects.LandingPage;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;

public class ProductSearchTests extends BaseUITest{

    @Test
    public void searchProductAndVerifyResults(){
        LandingPage landingPage = open("/index.php", LandingPage.class);
        landingPage
                .performSearchAndGiveResults("blouse")
                .shouldHave(size(1));
    }

    @Test
    public void contactSellerAndVerifySuccessMessagePresent(){
        ContactUsPage contactUsPage = open("/index.php?controller=contact", ContactUsPage.class);
        contactUsPage
                .contactSeller()
                .verifySuccessMessageIsPresent();
    }
}
