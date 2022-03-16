package demo;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;

public class DemoTest {

    @Before
    public void setup(){
        Configuration.browser = "firefox";
    }

    @Test
    public void sendFeedback() {
        Selenide.open("http://automationpractice.com/index.php");
        Selenide.$("#contact-link").click();
        Selenide.$("#id_contact").selectOptionByValue("1");
        Selenide.$("#email").val("test@toptal.com");
        Selenide.$("#id_order").val("R108");
        String imageFile = "/Users/renju/Devworx/intellij-workspace/TestFramework/src/main/resources/assets/testimg.png";
        Selenide.$("#fileUpload").sendKeys(imageFile);
        Selenide.$("#message").val("Complaint about order R108");
        Selenide.$("#submitMessage").click();
        Selenide.$(".alert.alert-success").shouldHave(Condition.text("Your message has been successfully sent to our team."));
    }
}
