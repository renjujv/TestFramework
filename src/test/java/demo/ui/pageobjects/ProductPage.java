package demo.ui.pageobjects;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ProductPage {

    public ProductPage changeSize(String size){
        String SIZE_SELECTOR = "#group_1";
        $(SIZE_SELECTOR).selectOption(size);
        return this;
    }

    public ProductPage addToCart(){
        String ADD_TO_CART_BUTTON = "p#add_to_cart > button";
        $(ADD_TO_CART_BUTTON).click();
        return this;
    }

    public void verifySuccessMessageIsPresent(){
        //TODO Need to debug the test failure due to locator
//        String ADDED_TO_CART_CARD_TITLE = "#layer_cart > div > div.layer_cart_product > h2";
        String ADDED_TO_CART_CARD_TITLE = "#layer_cart";
        String ADDED_TO_CART_SUCCESS_MESSAGE = "Product successfully added to your shopping cart";
        $(ADDED_TO_CART_CARD_TITLE).shouldHave(text(ADDED_TO_CART_SUCCESS_MESSAGE));
        //layer_cart
    }
}
