package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBuilderUtil {

    private RequestSpecBuilderUtil() {
    }

    /**
     * This method is to get request specification by Json with Content Type = "application/json"
     *
     * @return RequestSpecification
     */
    public static RequestSpecification specWithJson(RequestSpecification spec) {
        return specWithJson(0, spec);
    }

    /**
     * This method is to get request specification by Json
     *
     * @param contentTypeNumber 0 for "application/json", 1 for "application/javascript",
     *                          2 for "text/javascript"
     * @return RequestSpecification
     */
    public static RequestSpecification specWithJson(int contentTypeNumber, RequestSpecification spec) {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON.getContentTypeStrings()[contentTypeNumber])
                .addRequestSpecification(spec).build();
    }
}
