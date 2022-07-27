package steps;

import io.cucumber.docstring.DocString;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.UserGenerator;
import utils.UserPojo;
import utils.api_clients.GraphqlClient;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiGqlSteps {
    private static GraphqlClient gqlClient = new GraphqlClient();
    private static UserGenerator userGenerator = new UserGenerator();
    private static UserPojo user = userGenerator.generateUserData();
    private Response response;

    @Given("GQL Given I set up a basic url as {string}")
    public void gql_given_i_set_up_a_basic_url_as(String url) {
        gqlClient.setURL(url);
    }

    @When("I send a GQL request with body")
    public void i_send_a_gql_request_with_body(DocString docString) {
        response = gqlClient.gqlRequest(docString, user);
    }

    @Then("GQL Response code is {int}")
    public void gql_response_code_is(Integer code) {
        assertThat(response.getStatusCode()).isEqualTo(code);
    }

    @Then("GQL Response does not contains {string}")
    public void gql_response_does_not_contains(String string) {
        assertThat(response.getBody().asString()).doesNotContain(string);
    }

    @Then("GQL Response contains {string}")
    public void gql_response_contains(String string) {
        assertThat(response.getBody().asString()).contains(string);
    }

    @Then("GQL I save user data")
    public void gql_i_save_user_data() {
        gqlClient.saveID(response, user);
    }

    @Then("GQL Response contains correct user info")
    public void gql_response_contains_correct_user_info() {
        gqlClient.compareUsers(response, user);
    }
}
