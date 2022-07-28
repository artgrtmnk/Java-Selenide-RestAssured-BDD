package utils.api_clients;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.JsonFileReader;
import utils.UserPojo;

public abstract class BaseClient {
    private final JsonFileReader jsonFileReader = new JsonFileReader();
    private final String TOKEN = jsonFileReader.parseToken();
    protected RequestSpecification REQ_SPEC =
            new RequestSpecBuilder()
                    .addHeader("Authorization", "Bearer " + TOKEN)
                    .setContentType(ContentType.JSON)
                    .build();

    public void setURL(String url) {
        REQ_SPEC.baseUri(url);
    }
    public abstract void saveID(Response response, UserPojo user);

    public abstract void compareUsers(Response response, UserPojo user);
}
