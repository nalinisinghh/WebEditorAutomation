@Regression
Feature: check validation on page

  Background: 
    Given web editor page is open


  Scenario: Verify listitems count
    Given list item count match on Documentpage with DocumnetMap

  
  Scenario Outline: Verify highlighted tags in DocuementMap
    When select <para> in EditorPage
    Then corresponding tag should highlighted

    Examples: 
      | para                                   |
      | Why doesn’t my World Car change gears?|

   
    Scenario: perform add row functionality in table
    
    When table is exist on page
    Then perform add row functionality