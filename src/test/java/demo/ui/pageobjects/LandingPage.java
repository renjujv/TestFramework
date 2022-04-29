package demo.ui.pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.*;

public class LandingPage {

    public SearchResultsPage performSearch(String searchText){
        String SEARCH_BAR_TOP = "#search_query_top";
        $(SEARCH_BAR_TOP).val(searchText).submit();
        return Selenide.page(SearchResultsPage.class);
    }
}
