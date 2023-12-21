package steps;

import static com.xebialabs.restito.builder.stub.StubHttp.whenHttp;
import static com.xebialabs.restito.semantics.Action.resourceContent;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.Assert;
import org.unitils.reflectionassert.ReflectionAssert;

import com.xebialabs.restito.server.StubServer;

import apis.AbstractBaseRequest;
import apis.endpoints.JsonPath;
import apis.services.OpenWeatherMapService;
import apis.services.RestitoMockService;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.openweather.Coord;
import model.openweather.SearchValidCityResponseModel;
import utilities.ConvertUtil;

public class SearchCitySteps extends AbstractBaseRequest {

    private Response response;
    private OpenWeatherMapService openWeatherMap = new OpenWeatherMapService();

    private String cityName;

    /**
     * Demo for Test Data Management.
     *  - Loading the test data (cityName) of the environment STAGING or DEMO base on the parameter -Denvironment={environment}
     */
    @When("^Using Test Data Management, I calls the api to search the weather forecast of the city$")
    public void getWeatherForecastByName() {
        response = openWeatherMap.getWeatherForecast(dataHandler.getTestData("OpenWeatherMap", "name"));
    }

    @Then("^I verify the fields in the response body$")
    public void verifyTheSpecifiedFieldsInResponseBody() {

        SearchValidCityResponseModel autualResponse = response.getBody().as(SearchValidCityResponseModel.class);

        Assert.assertEquals(autualResponse.getId(), dataHandler.getTestData("OpenWeatherMap", "id"), "The city ID is NOT correct!");
        Assert.assertEquals(autualResponse.getName(), dataHandler.getTestData("OpenWeatherMap", "name"), "The city Name is NOT correct!");
        Assert.assertEquals(autualResponse.getTimezone(), dataHandler.getTestData("OpenWeatherMap", "timezone"), "The city timezone is NOT correct!");

        Coord coord = autualResponse.getCoord();
        Assert.assertEquals(coord.getLon(), dataHandler.getTestData("OpenWeatherMap", "coord_lon"), "The city geographic coordinates are NOT correct!");
        Assert.assertEquals(coord.getLat(), dataHandler.getTestData("OpenWeatherMap", "coord_lat"), "The city geographic coordinates are NOT correct!");
    }

    /**
     * Demo for Test Data Driven.
     *  - Loading the cityName & State from the keyword Examples in the cucumber feature
     */
    @When("^I calls the api to search the weather forecast of the city by \"(.*)\" and \"(.*)\"$")
    public void getWeatherForecastByNameAndState(String name, String state) {
        cityName = name;
        response = openWeatherMap.getWeatherForecast(name, state);
    }

    @When("^I calls the api to search the weather forecast of the city by \"(.*)\", \"(.*)\", \"(.*)\"$")
    public void getWeatherForecastByName_State_CountryCode(String name, String state, String code) {
        cityName = name;
        response = openWeatherMap.getWeatherForecast(cityName, state, code);
    }

    @When("^I want to use Restito to demo Mock API and search the weather forecast of the city$")
    public void getWeatherForecastWithRestito() {

        /**
         * Restito Mock Service
         *
         * @param hostName   example: http://localhost
         * @param basePath   example: /account/1
         * @param dataSource path to data source, only accept .json, .xml file
         */

        StubServer server = new StubServer().run();

        whenHttp(server)
                .match(String.valueOf(RestAssured.get("/account/1")))
                .then(resourceContent("MockJSONResponse.json"));

        RestitoMockService mockService = new RestitoMockService();
        response = mockService.sendGetRequest(server.getPort());

        server.stop();
    }

    @When("^I calls the api to search the weather forecast of the invalid city (.*)$")
    public void invalidRequest(String name) {
        cityName = name;
        response = openWeatherMap.getWeatherForecast(cityName);
    }

    @Then("^I verify the HTTP response code is (.*)$")
    public void verifyAccessCardIsCreatedUpdatedSuccessful(String code) {
        Assert.assertEquals(String.valueOf(response.getStatusCode()), code, "Verify response status is " + code);
    }

    @Then("^I verify the error message \"(.*)\"$")
    public void verifyTheErrorMessage(String message) {
        Assert.assertEquals(response.jsonPath().getString("message"), "city not found",
                            "The error response message does not like to expect!");
    }

    @Then("^I verify the error response schema$")
    public void verifyTheErrorResponseSchema() {
        response.then().assertThat().body(matchesJsonSchemaInClasspath(JsonPath.SEARCH_INVALID_CITY_RESPONSE_SCHEMA));
    }

    @Then("^I verify the field values: \"(.*)\", \"(.*)\", \"(.*)\", \"(.*)\", \"(.*)\" in the response body returned$")
    public void verifyTheSpecifiedFieldsInResponseBody(String id, String name, String timezone, String coord_lon, String coord_lat) {

        SearchValidCityResponseModel autualResponse = response.getBody().as(SearchValidCityResponseModel.class);

        Assert.assertEquals(autualResponse.getId(), id, "The city ID is NOT correct!");
        Assert.assertEquals(autualResponse.getName(), name, "The city Name is NOT correct!");
        Assert.assertEquals(autualResponse.getTimezone(), timezone, "The city timezone is NOT correct!");

        Coord coord = autualResponse.getCoord();
        Assert.assertEquals(coord.getLon(), coord_lon, "The city geographic coordinates are NOT correct!");
        Assert.assertEquals(coord.getLat(), coord_lat, "The city geographic coordinates are NOT correct!");
    }

    @Then("^I verify the response body is reflected with the expected JSON$")
    public void verifyTheReflectionOfResponseBody() {
        SearchValidCityResponseModel autualResponse = response.getBody().as(SearchValidCityResponseModel.class);

        SearchValidCityResponseModel expectedResponse = ConvertUtil.convertJsonFileToObject(
                "src/test/resources/MockJSONResponse.json",
                SearchValidCityResponseModel.class);

        ReflectionAssert
                .assertReflectionEquals("Asserts that two objects are equal. Verify response body values", expectedResponse, autualResponse);
    }
}
