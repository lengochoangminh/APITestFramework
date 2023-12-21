Feature: Endpoint /data/2.5/weather - Invalid Request Test

  @NegativeTest
  Scenario: Search the invalid city & verify the response code & body
    When I calls the api to search the weather forecast of the invalid city HoChiMinh
    Then I verify the HTTP response code is 404
    And I verify the error message "city not found"