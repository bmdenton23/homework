Feature: Checkout

  Scenario: checkout
    Given I checkout 3 apples    
    Then my cost is 3.0
    
    
   Scenario: checkout
    Given I checkout 5 banana         
    Then my cost is 7.5  
    
   Scenario: checkout
    Given I checkout 12 grapes         
    Then my cost is 24.0    

    
