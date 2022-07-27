package utils.api_clients;

import io.cucumber.docstring.DocString;
import io.restassured.response.Response;
import utils.UserPojo;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RestClient extends BaseClient {
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

    @Override
    public void saveID(Response response, UserPojo user) {
        user.setId(response.path("id"));
    }

    @Override
    public void compareUsers(Response response, UserPojo user) {
        UserPojo responseUser = response.as(UserPojo.class);
        assertThat(responseUser).usingRecursiveComparison().isEqualTo(user);
    }
}
