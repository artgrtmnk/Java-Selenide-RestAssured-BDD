package utils.api_clients;

import io.cucumber.docstring.DocString;
import io.restassured.response.Response;
import org.json.JSONObject;
import utils.DefaultDataValidator;
import utils.UserPojo;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GraphqlClient extends BaseClient {
    private DefaultDataValidator defaultDataValidator = new DefaultDataValidator();

    public Response gqlRequest(DocString docString, UserPojo user) {
        JSONObject json = new JSONObject();
        json.put("query",docString.getContent());
        String query = defaultDataValidator.checkForDefaultData(json, user);

        return given()
                .spec(REQ_SPEC)
                .body(query)
                .post();
    }

    @Override
    public void saveID(Response response, UserPojo user) {
        user.setId(response.path("data.createUser.user.id"));
    }

    @Override
    public void compareUsers(Response response, UserPojo user) {
        UserPojo responseUser = response.jsonPath()
                                        .getObject("data.user", UserPojo.class);

        assertThat(responseUser).usingRecursiveComparison().isEqualTo(user);
    }
}