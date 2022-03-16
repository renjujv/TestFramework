package demo;

import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.By;

public class GoogleSearchPage {
    public GoogleResultsPage search(String query) {
        Selenide.$(By.name("q")).setValue(query).pressEnter();
        return Selenide.page(GoogleResultsPage.class);
    }
}

