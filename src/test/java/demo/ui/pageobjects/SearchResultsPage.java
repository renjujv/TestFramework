package demo.ui.pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultsPage {
    private final ElementsCollection searchResultProductsContainer = $$(".product_list > li > .product-container");
    private final ElementsCollection searchResultProductNames = $$("div.product-container a.product-name");
    private final By searchResultFirstProductTitle = By.cssSelector("div.product-container > div.right-block > h5 > a");

    public ElementsCollection getResults(){
        return searchResultProductsContainer;
    }

    public ElementsCollection getProductNames(){ return searchResultProductNames; }

    public ProductPage viewProductDetails(){
        searchResultProductsContainer
                .first()
                .scrollIntoView(false)
                .hover()
                .find(searchResultFirstProductTitle)
                .click();
        return Selenide.page(ProductPage.class);
    }
}
