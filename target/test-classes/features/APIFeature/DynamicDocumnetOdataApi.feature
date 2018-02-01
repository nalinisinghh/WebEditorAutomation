 @API 
Feature: Verify Dynamic data response code


  Scenario Outline: Verify the list of documents with metadata
    Given when we hit <uri>
    Then get reponse from server

    Examples: 
      | uri                                                                                        |
      | http://vagrant.ptcnet.ptc.com:2280/Windchill/servlet/odata/DynamicDocMgmt/DynamicDocuments |

  Scenario: Verify metadata of document based on filename
    When i hit uri including filename
    Then match with filename get in response
   
  
  Scenario: Verify content of the file in response
  When i hit uri with filename for getting content
  Then verify reponse code 
  
  
