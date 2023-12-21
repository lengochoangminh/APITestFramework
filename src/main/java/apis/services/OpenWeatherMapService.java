package apis.services;

import apis.AbstractBaseRequest;
import apis.endpoints.APIEndpoint;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.RequestSpecBuilderUtil;

public class OpenWeatherMapService extends AbstractBaseRequest {

    public OpenWeatherMapService() { }

    public Response getWeatherForecast(String city) {
        return sendGetRequest(city);
    }

    public Response getWeatherForecast(String city, String stateCode) {
        return sendGetRequest(city + "," + stateCode);
    }

    public Response getWeatherForecast(String city, String stateCode, String countryCode) {
        return sendGetRequest(city + "," + stateCode + "," + countryCode);
    }

    public Response sendGetRequest(String query) {
        final RequestSpecification spec = RequestSpecBuilderUtil.specWithJson
                (new RequestSpecBuilder()
                         // http://api.openweathermap.org/data/2.5/weather
                         .setBaseUri(dataHandler.getEnvironmentData("OPEN_WEATHER_MAP_URL").concat(APIEndpoint.GET_WEATHER))
                         .addParam("q", query)
                         .addParam("APPID", dataHandler.getEnvironmentData("OPEN_WEATHER_MAP_API_KEY"))
                         .build());

        return sendAGetRequest(spec);
    }
}
