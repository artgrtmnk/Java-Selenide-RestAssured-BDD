package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public SelenideElement userNameField = $("label[for='sso_username']");
    public SelenideElement passwordField = $("#ssopassword");
    public SelenideElement submitButton = $("#signin_button");
    public SelenideElement errorMessage = $("#errormsg");

    public void openPage() {
        Selenide.open("https://profile.oracle.com/");
        this.userNameField.should(exist);
        this.passwordField.should(exist);
        this.submitButton.should(exist);
    }

    public void enterUserName(String username) {
        this.userNameField.shouldBe(visible).val(username);
    }

    public void enterPassword(String password) {
        this.passwordField.val(password);
    }

    public void clickSubmitButton() {
        this.submitButton.click();
    }

    public void isErrorMessageExists(String errorMessage) {
        this.errorMessage.shouldHave(Condition.text(errorMessage));
    }
}
