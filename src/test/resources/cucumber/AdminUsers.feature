@Admin
Feature: Admin
  As a b2b admin user
  I should be able to administer the system
  So that other users can use b2b application as desired


  Background:
    Given that the user "admin_name" logged in as "admin_user" and "password"


  Scenario: Creation of Custom Role and inherit System Admin role
    Given the user is on "Roles and Permissions" tab on admin page
    When he creates a new role named "CustomSystemAdminRole" and inherits permissions from "SystemAdmin" role
    And user navigates to the Users tab
    When he assigns role "CustomSystemAdminRole" to "learner_username"
    And User "deonaraya1@gmail.com" logout

  Scenario: Login functionality
    Given user navigates to Define Expertise Tab
    And User "deonaraya1@gmail.com" logout

