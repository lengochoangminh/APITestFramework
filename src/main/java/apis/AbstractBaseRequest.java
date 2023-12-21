package apis;

import environment.EnvironmentHandler;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.RequestUtil;

public abstract class AbstractBaseRequest {

    protected static final EnvironmentHandler dataHandler = EnvironmentHandler.getInstance();

    protected Response sendAGetRequest(RequestSpecification additionalSpec) {
        return RequestUtil.get(additionalSpec);
    }

    protected <T> T sendAGetRequest(RequestSpecification additionalSpec, Class<T> clazz) {
        return RequestUtil.get(additionalSpec, clazz);
    }

    protected Response sendAPostRequest(RequestSpecification additionalSpec) {
        return RequestUtil.post(additionalSpec);
    }

    protected <T> T sendAPostRequest(RequestSpecification additionalSpec, Class<T> clazz) {
        return RequestUtil.post(additionalSpec, clazz);
    }

    protected Response sendAPutRequest(RequestSpecification additionalSpec) {
        return RequestUtil.put(additionalSpec);
    }

    protected <T> T sendAPutRequest(RequestSpecification additionalSpec, Class<T> clazz) {
        return RequestUtil.put(additionalSpec, clazz);
    }

    protected Response sendADeleteRequest(RequestSpecification additionalSpec) {
        return RequestUtil.delete(additionalSpec);
    }

    protected Response sendADeleteRequest(RequestSpecification additionalSpec, String param) {
        return RequestUtil.delete(additionalSpec, param);
    }

    protected Response sendAPatchRequest(RequestSpecification additionalSpec) {
        return RequestUtil.patch(additionalSpec);
    }
}
