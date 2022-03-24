package demo.ui.pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.*;

public class LandingPage {
    private final String SEARCH_BAR_TOP = "#search_query_top";
    private final String SEARCH_RESULTS = "#product_list > li";
    private final String PRODUCT_TITLE = "div.product-container > div.right-block > h5 > a";


    public ElementsCollection performSearchAndGiveResults(String searchText){
        $(SEARCH_BAR_TOP).val(searchText).submit();
        return $$(SEARCH_RESULTS);
    }

    public LandingPage performSearch(String searchText){
        $(SEARCH_BAR_TOP).val(searchText).submit();
        return this;
    }

    public ProductPage viewProductDetails(){
        $(SEARCH_RESULTS)
                .scrollIntoView(false).hover()
                .find(PRODUCT_TITLE).click();
        return Selenide.page(ProductPage.class);
    }
}
