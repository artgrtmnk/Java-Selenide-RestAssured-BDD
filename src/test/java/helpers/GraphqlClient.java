package helpers;

import io.cucumber.docstring.DocString;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GraphqlClient {
    private final String TOKEN = "594add4a6e80e6bfeb2b345424060aad3bba0d538628eeff491d774957ee834a";
    private final RequestSpecification REQ_SPEC =
            new RequestSpecBuilder()
                    .setBaseUri("https://gorest.co.in/public/v2/graphql")
                    .addHeader("Authorization", "Bearer " + TOKEN)
                    .setContentType(ContentType.JSON)
                    .build();
    private final DefaultDataValidator defaultDataValidator = new DefaultDataValidator();
    public Response gqlRequest(DocString docString, UserPojo user) {
        JSONObject json = new JSONObject();
        json.put("query",docString.getContent());
        String query = defaultDataValidator.checkForDefaultData(json, user);

        return given()
                .spec(REQ_SPEC)
                .body(query)
                .post();
    }

    public void saveID(Response response, UserPojo user) {
        user.setId(response.path("data.createUser.user.id"));
    }

    public void compareUsers(Response response, UserPojo user) {
        UserPojo responseUser = response
                .jsonPath()
                .getObject("data.user", UserPojo.class);
        assertThat(responseUser).usingRecursiveComparison().isEqualTo(user);
    }
}