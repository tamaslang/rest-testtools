@restApiIntegration
Feature: Example Resource Integration Test

  Scenario: test get a stringlist
    Given the web context is set
    When client request GET /api/example/stringlist
    Then the response code should be 200
    Then the result string should be:
    """
    ["a","b"]
    """

  Scenario: test get a stringlist with json match
    Given the web context is set
    When client request GET /api/example/stringlist
    Then the response code should be 200
    Then the result json should be:
    """
    ["a","b"]
    """

  Scenario: test post a stringlist
    Given the web context is set
    When client request PUT /api/example/stringlist/reverse with json data:
    """
    ["a","b"]
    """
    Then the response code should be 200
    Then the result string should be:
    """
    ["b","a"]
    """