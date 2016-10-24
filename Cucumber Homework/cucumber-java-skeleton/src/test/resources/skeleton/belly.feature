Feature: Belly

  Scenario: a few cukes
    Given I have 42 cukes in my belly
    When I wait 1 hour
    Then my belly should growl
    
  Scenario: a few cukes
    Given I have 40 cukes in my belly
    When I wait 0 hour
    Then my belly is silent
    
