package helpers;

import io.cucumber.docstring.DocString;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RestClient {
    private final String TOKEN = "594add4a6e80e6bfeb2b345424060aad3bba0d538628eeff491d774957ee834a";

    private final RequestSpecification REQ_SPEC =
            new RequestSpecBuilder()
                    .setBaseUri("https://gorest.co.in/public/v2/users")
                    .addHeader("Authorization", "Bearer " + TOKEN)
                    .setContentType(ContentType.JSON)
                    .build();

    private int id;

    public Response getUserList() {
        return given()
                .spec(REQ_SPEC)
                .basePath("/")
                .get();
    }

    public Response getUserInfo(UserPojo user) {
        id = user.getId();
        return given()
                .spec(REQ_SPEC)
                .basePath("/" + id)
                .get();
    }

    public Response createUser(UserPojo user) {
        return given()
                .spec(REQ_SPEC)
                .basePath("/")
                .body(user)
                .post();
    }

    public Response patchUser(DocString docString) {
        return given()
                .spec(REQ_SPEC)
                .basePath("/" + id)
                .body(docString.getContent())
                .patch();
    }

    public Response deleteUser() {
        return given()
                .spec(REQ_SPEC)
                .basePath("/" + id)
                .delete();
    }

    public void saveID(Response response, UserPojo user) {
        user.setId(response.path("id"));
    }

    public void compareUsers(Response response, UserPojo user) {
        UserPojo responseUser = response.as(UserPojo.class);
        assertThat(responseUser).usingRecursiveComparison().isEqualTo(user);
    }
}
