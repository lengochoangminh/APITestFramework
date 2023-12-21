Feature: Endpoint /data/2.5/weather - Valid Request Test

  @PositiveTest
  Scenario: Search the weather forecast by the City Name
    When Using Test Data Management, I calls the api to search the weather forecast of the city
    Then I verify the HTTP response code is 200
    And  I verify the fields in the response body

  Scenario Outline: Search the weather forecast by the City Name and State Code
    When I calls the api to search the weather forecast of the city by "<name>" and "<state>"
    Then I verify the HTTP response code is 200
    And  I verify the field values: "<id>", "<name>", "<timezone>", "<coord_lon>", "<coord_lat>" in the response body returned

    @PositiveTest
    Examples:
      | name        | state   | id        | timezone  | coord_lon   | coord_lat |
      | Manchester  | UK      | 2643123   | 0         | -2.2374     | 53.4809   |
      | Paris       | FR      | 2988507   | 3600      | 2.3488      | 48.8534   |