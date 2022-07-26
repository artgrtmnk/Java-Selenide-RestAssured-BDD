package steps;

import helpers.RestClient;
import helpers.UserGenerator;
import helpers.UserPojo;
import io.cucumber.docstring.DocString;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiRestSteps {
    private static RestClient restClient;
    private static UserGenerator userGenerator = new UserGenerator();
    private static UserPojo user = userGenerator.generateUserData();;
    private Response response;

    @Before
    public static void setup() {
        restClient = new RestClient();
    }
    @Given("I set up a basic url as {string}")
    public void i_set_up_a_basic_url_as(String url) {
        RestAssured.baseURI = url;
    }

    @When("I send a Get user list request")
    public void i_send_a_get_user_list_request() {
        response = restClient.getUserList();
    }

    @When("I send a Post create user request")
    public void i_send_a_post_create_user_request() {
        response = restClient.createUser(user);
    }

    @When("I send a Get created user request")
    public void i_send_a_get_created_user_request() {
        response = restClient.getUserInfo(user);
    }

    @When("I send a Patch user request with body")
    public void i_send_a_patch_user_request_with_body(DocString docString) {
        response = restClient.patchUser(docString);
    }

    @When("I send a Delete user request")
    public void i_send_a_delete_user_request() {
        response = restClient.deleteUser();
    }

    @Then("Response code is {int}")
    public void response_code_is(Integer code) {
        assertThat(response.getStatusCode()).isEqualTo(code);
    }

    @Then("Response contains {string}")
    public void response_contains(String substring) {
        assertThat(response.getBody().asString()).contains(substring);
    }

    @Then("Response contains correct user info")
    public void response_contains_correct_user_info() {
        restClient.compareUsers(response, user);
    }

    @Then("I save user id")
    public void i_save_user_id() {
        restClient.saveID(response, user);
    }


}
