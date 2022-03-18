package ui.pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.*;

public class LandingPage {
    private final String SEARCH_BAR_TOP = "#search_query_top";
    private final String CONTACT_US_LINK = "#contact-link";
    private final String SEARCH_RESULTS = "#product_list>li";

    public ElementsCollection search(String searchText){
        $(SEARCH_BAR_TOP).val(searchText).submit();
        return $$(SEARCH_RESULTS);
    }

    public ContactUsPage gotoContactPage() {
        $(CONTACT_US_LINK).click();
        return Selenide.page(ContactUsPage.class);
    }
}
