@Expertise
Feature: Experts
  As a b2b user
  I should be able to view experts page
  So that I can look up all experts

  Background:
    Given that the user "admin_name" logged in as "admin_user" and "password"

  Scenario: Define an expertise and assign the user A expert in the same
    Given user navigates to Define Expertise Tab
    When user define a new TimeStamped expertise "SME"
    And user navigates to the Users tab
    And assign the TimeStamped expertise "SME" to the user "mahone_7@mailinator.com"
    Then user "mahone_7@mailinator.com" should be marked expert in expertise "SME"

