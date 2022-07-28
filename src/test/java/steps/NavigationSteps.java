package steps;

import io.cucumber.java.en.Given;
import pages.LoginPage;

public class NavigationSteps {
    LoginPage loginPage = new LoginPage();

    @Given("User has opened Oracle Profile page")
    public void userOpensProfilePage() {
        loginPage.openPage();
    }
}
