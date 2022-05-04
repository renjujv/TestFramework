package demo.ui.pageobjects;

import com.codeborne.selenide.SelenideElement;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ContactUsPage {
    //TODO move locators to hierarchical json or yaml file
    private final SelenideElement subjectInput = $("#id_contact");
    private final SelenideElement emailInput = $("#email");
    private final SelenideElement orderReferenceLabel = $("#id_order");
    private final SelenideElement fileUploadInput = $("#fileUpload");
    private final SelenideElement messageBodyInput = $("#message");
    private final SelenideElement sendButton = $("#submitMessage");
    private final SelenideElement successMessageLabel = $(".alert.alert-success");

    public ContactUsPage selectSubjectHeading(String value) {

        subjectInput.selectOptionByValue(value);
        return this;
    }

    public ContactUsPage enterEmail(String value) {

        emailInput.val(value);
        return this;
    }

    public ContactUsPage enterOrderReference(String value) {

        orderReferenceLabel.val(value);
        return this;
    }

    public ContactUsPage uploadFile(File file) {

        fileUploadInput.uploadFile(file);
        return this;
    }

    public ContactUsPage enterMessage(String value) {
        messageBodyInput.val(value);
        return this;
    }

    public void submitForm() {
        sendButton.click();
    }

    public ContactUsPage contactSeller(){
        String sampleUploadFilePath = "src/test/resources/demo/testimg.png";
        File file = new File(sampleUploadFilePath);
        this.selectSubjectHeading("2")
                .enterEmail("test@test.com")
                .enterOrderReference("C123")
                .uploadFile(file)
                .enterMessage("Sample feedback text message")
                .submitForm();
        return this;
    }

    public void verifySuccessMessageIsPresent(){
        String SUCCESS_MESSAGE_TEXT = "Your message has been successfully sent to our team.";
        successMessageLabel.should(text(SUCCESS_MESSAGE_TEXT));
    }
}
