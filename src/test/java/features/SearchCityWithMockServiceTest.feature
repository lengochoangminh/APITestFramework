Feature: API Mock Demo with Restito

  @MockAPITest
  Scenario: Search the weather forecast by the City Name, State Code and Country Code
    When I want to use Restito to demo Mock API and search the weather forecast of the city
    Then I verify the HTTP response code is 200
    And I verify the response body is reflected with the expected JSON