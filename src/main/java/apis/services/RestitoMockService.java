package apis.services;

import apis.AbstractBaseRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.RequestSpecBuilderUtil;

public class RestitoMockService extends AbstractBaseRequest{

    public Response sendGetRequest(int port) {
        final RequestSpecification spec = RequestSpecBuilderUtil.specWithJson
                (new RequestSpecBuilder()
                         .setPort(port)
                         .setBaseUri("http://127.0.0.1")
                         .build()
                );

        return sendAGetRequest(spec);
    }

}
