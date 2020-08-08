Feature: Booking best flight ticket

  Scenario Outline: To verify user is able to book the best flight ticket
    Given I open the flight booking portal in "<browser>" browser
    And I select "Delhi (DEL)" as source
    And I select "Hyderabad (HYD)" as destination
    When I get the fares
    Then I should be able to select the best itinerary

    Examples: 
      | browser |
      | chrome  |
      | safari  |
