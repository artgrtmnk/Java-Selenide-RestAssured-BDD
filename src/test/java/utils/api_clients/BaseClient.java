package utils.api_clients;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.UserPojo;

public abstract class BaseClient {
    private final String TOKEN = "594add4a6e80e6bfeb2b345424060aad3bba0d538628eeff491d774957ee834a";
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
