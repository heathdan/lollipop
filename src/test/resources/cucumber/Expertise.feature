@Expertise
Feature: Experts
  As a b2b user
  I should be able to view experts page
  So that I can look up all experts

  Background:
    Given that the user "admin_name" logged in as "admin_user" and "password"

    Scenario: User can not delete an expertise once asigned to a user
      Given user navigates to Define Expertise Tab
      When user define a new TimeStamped expertise "ASIGN_EXP"
      And user navigates to the Users tab
      And assign the TimeStamped expertise "ASIGN_EXP" to the user "learner_username"
      Then user navigates to Define Expertise Tab
      And user verifies that expertise "ASIGN_EXP" is assigned successfully
      Then user should not find an option to delete the assigned expertise "ASIGN_EXP"
      And User "admin_user" logout

  Scenario: Define an expertise and assign the user A expert in the same
    Given user navigates to Define Expertise Tab
    When user define a new TimeStamped expertise "SME"
    And user navigates to the Users tab
    And assign the TimeStamped expertise "SME" to the user "learner_username"
    Then user "learner_username" should be marked expert in expertise "SME"
    And User "admin_user" logout

  Scenario: Delete unused expertise
    Given user navigates to Define Expertise Tab
    Then user define a new TimeStamped expertise "TobeDeleted"
    And user search for the TimeStamped expertise "TobeDeleted"
    Then admin should be able to delete the unused expertise "TobeDeleted"
    And User "admin_user" logout








