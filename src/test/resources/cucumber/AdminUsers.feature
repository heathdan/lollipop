@Functional
Feature: Admin
  As a b2b admin user
  I should be able to administer the system
  So that other users can use b2b application as desired


  Background:

  Scenario: User sign in
  Given that the user "admin_name" logged in as "deonaraya1@gmail.com" and "Cisco@1234"


  Scenario: As a user I should be able to search for a user and print his details.
    Given user navigates to the Users tab
    Then user searches for a user "mahone_7@mailinator.com" and print its details
    And user performs operations on expertise popup

  Scenario: As a user I should be able to navigate to all the tabs on users page
    Given user navigates to the Users tab
    Then user navigates to Roles & Permission tab
    Then user navigates to Define Expertise Tab


  Scenario: When user logs out
    Given user "deonaraya1@gmail.com" logout

