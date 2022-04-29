package demo.ui.pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultsPage {
    private final String SEARCH_RESULTS = ".product_list > li > .product-container";

    public ElementsCollection getResults(){
        return $$(SEARCH_RESULTS);
    }

    public ElementsCollection getProductNames(){
        String RESULT_PRODUCT_NAMES = "div.product-container a.product-name";
        return $$(RESULT_PRODUCT_NAMES);
    }

    public ProductPage viewProductDetails(){
        String PRODUCT_TITLE = "div.product-container > div.right-block > h5 > a";
        $(SEARCH_RESULTS)
                .scrollIntoView(false).hover()
                .find(PRODUCT_TITLE).click();
        return Selenide.page(ProductPage.class);
    }
}
