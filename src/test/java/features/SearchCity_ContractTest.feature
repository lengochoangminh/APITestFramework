Feature: Endpoint /data/2.5/weather - Validate the schema contract of the invalid Request

@ContractTest
Scenario: Search the invalid city & verify the response schema
When I calls the api to search the weather forecast of the invalid city HoChiMinh
Then I verify the HTTP response code is 404
And I verify the error response schema