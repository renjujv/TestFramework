package ui.pageobjects;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.*;

public class LandingPage {
    private final String SEARCH_BAR_TOP = "#search_query_top";
    private final String CONTACT_US_LINK = "#contact-link";
    private final String SEARCH_RESULTS = "#product_list>li";

    public ElementsCollection performSearchAndGiveResults(String searchText){
        $(SEARCH_BAR_TOP).val(searchText).submit();
        return $$(SEARCH_RESULTS);
    }
}
