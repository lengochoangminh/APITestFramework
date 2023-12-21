package apis.endpoints;

public final class JsonPath {

    private JsonPath() { }

    private static final String USER_DIRECTORY = System.getProperty("user.dir");

    // API Requests

    // API Responses
    public static final String RESPONSE_PATH = USER_DIRECTORY + "/src/test/resources/responseTemplate/";

    // API Schema
    public static final String SEARCH_INVALID_CITY_RESPONSE_SCHEMA = "jsonSchema/searchInvalidCitySchema.json";

}
