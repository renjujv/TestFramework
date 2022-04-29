package demo.ui.pageobjects;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ContactUsPage {
    //TODO move locators to hierarchical json or yaml file

    public ContactUsPage selectSubjectHeading(String value) {
        String SUBJECT_HEADING = "#id_contact";
        $(SUBJECT_HEADING).selectOptionByValue(value);
        return this;
    }

    public ContactUsPage enterEmail(String value) {
        String SENDER_EMAIL = "#email";
        $(SENDER_EMAIL).val(value);
        return this;
    }

    public ContactUsPage enterOrderReference(String value) {
        String ORDER_REF = "#id_order";
        $(ORDER_REF).val(value);
        return this;
    }

    public ContactUsPage uploadFile(File file) {
        String FILE_UPLOAD_INPUT = "#fileUpload";
        $(FILE_UPLOAD_INPUT).uploadFile(file);
        return this;
    }

    public ContactUsPage enterMessage(String value) {
        String MESSAGE_BODY = "#message";
        $(MESSAGE_BODY).val(value);
        return this;
    }

    public void submitForm() {
        String SEND_BUTTON = "#submitMessage";
        $(SEND_BUTTON).click();
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
        String SUCCESS_MESSAGE = ".alert.alert-success";
        String SUCCESS_MESSAGE_TEXT = "Your message has been successfully sent to our team.";
        $(SUCCESS_MESSAGE).should(text(SUCCESS_MESSAGE_TEXT));
    }
}
