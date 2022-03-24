package demo.ui.pageobjects;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ProductPage {
    private final String SIZE_SELECTOR = "#group_1";
    private final String AVAILABILITY_STATUS = "#availability_statut > #availability_value";
    private final String ADD_TO_CART_BUTTON = "p#add_to_cart>button";
    private final String ADDED_TO_CART_CARD_TITLE = "#layer_cart>div>div>span.title";

    private final String ADDED_TO_CART_SUCCESS_MESSAGE = "Product successfully added to your shopping cart";


    public ProductPage changeSize(String size){
        $(SIZE_SELECTOR).selectOption(size);
        return this;
    }

    public ProductPage verifyAvailabilityStatus(String availabilityStatus){
        $(AVAILABILITY_STATUS).shouldHave(text(availabilityStatus));
        return this;
    }

    public ProductPage addToCart(){
        $(ADD_TO_CART_BUTTON).click();
        return this;
    }

    public void verifySuccessMessageIsPresent(){
        $(ADDED_TO_CART_CARD_TITLE).shouldHave(text(ADDED_TO_CART_SUCCESS_MESSAGE));
    }
}
