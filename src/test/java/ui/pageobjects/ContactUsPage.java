package ui.pageobjects;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;

public class ContactUsPage {
    private final String SUBJECT_HEADING = "#id_contact";
    private final String SENDER_EMAIL = "#email";
    private final String ORDER_REF = "#id_order";
    private final String FILE_UPLOAD_INPUT = "#fileUpload";
    private final String MESSAGE_BODY = "#message";
    private final String SEND_BUTTON = "#submitMessage";

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

}
