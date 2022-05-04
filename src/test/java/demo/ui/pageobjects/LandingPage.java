package demo.ui.pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class LandingPage {
    private final SelenideElement searchQueryInput = $("#search_query_top");

    public SearchResultsPage performSearch(String searchText){
        searchQueryInput.val(searchText).submit();
        return Selenide.page(SearchResultsPage.class);
    }
}
