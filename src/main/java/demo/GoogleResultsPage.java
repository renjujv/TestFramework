package demo;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;

public class GoogleResultsPage {
    public ElementsCollection results() {
        return Selenide.$$("#ires li.g");
    }
}
