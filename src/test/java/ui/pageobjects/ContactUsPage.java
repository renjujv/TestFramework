package ui.pageobjects;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ContactUsPage {
    private final String SUBJECT_HEADING = "#id_contact";
    private final String SENDER_EMAIL = "#email";
    private final String ORDER_REF = "#id_order";
    private final String FILE_UPLOAD_INPUT = "#fileUpload";
    private final String MESSAGE_BODY = "#message";
    private final String SEND_BUTTON = "#submitMessage";
    private final String SUCCESS_MESSAGE = ".alert.alert-success";

    private final String sampleUploadFilePath = "src/test/resources/testimg.png";
    private final String SUCCESS_MESSAGE_TEXT = "Your message has been successfully sent to our team.";

    public ContactUsPage selectSubjectHeading(String value) {
        $(SUBJECT_HEADING).selectOptionByValue(value);
        return this;
    }

    public ContactUsPage enterEmail(String value) {
        $(SENDER_EMAIL).val(value);
        return this;
    }

    public ContactUsPage enterOrderReference(String value) {
        $(ORDER_REF).val(value);
        return this;
    }

    public ContactUsPage uploadFile(File file) {
        $(FILE_UPLOAD_INPUT).uploadFile(file);
        return this;
    }

    public ContactUsPage enterMessage(String value) {
        $(MESSAGE_BODY).val(value);
        return this;
    }

    public void submitForm() {
        $(SEND_BUTTON).click();
    }

    public ContactUsPage contactSeller(){
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
        $(SUCCESS_MESSAGE).should(text(SUCCESS_MESSAGE_TEXT));
    }
}
