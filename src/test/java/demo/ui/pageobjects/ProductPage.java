package demo.ui.pageobjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ProductPage {
    private final SelenideElement sizeSelectorWheel = $("#group_1");
    private final SelenideElement addToCartButton = $("p#add_to_cart > button");
    private final SelenideElement successMessageOverlay = $("#layer_cart");

    public ProductPage changeSize(String size){
        sizeSelectorWheel.selectOption(size);
        return this;
    }

    public ProductPage addToCart(){
        addToCartButton.click();
        return this;
    }

    public void verifySuccessMessageIsPresent(){
        //FIXME Need to debug the test failure due to locator "#layer_cart > div > div.layer_cart_product > h2"
        String ADDED_TO_CART_SUCCESS_MESSAGE = "Product successfully added to your shopping cart";
        successMessageOverlay.shouldHave(text(ADDED_TO_CART_SUCCESS_MESSAGE));
    }
}
