
Feature: Admin
  As a b2b admin user
  I should be able to administer the system
  So that other users can use b2b application as desired

  Background:
    Given that the user "admin_name" logged in as "admin_user" and "password"

# Scenario: Creation of Custom Role and inherit System Admin role
#   Given the user is on Roles and Permissions tab on admin page
#   When he creates a new role named "CustomSystemAdminRole" and inherits permissions from "SystemAdmin" role
#   And user navigates to the Users tab
#   When he assigns role "CustomSystemAdminRole" to "auto_kct2learner@mailinator.com"
#   And User "deonaraya1@gmail.com" logout

#  Scenario: Bulk user CSV upload by Admin
#  Given the user is on Users tab on admin page
#  When he uploads a csv file "upload_users" to on board user "Z"
#  Then user in "upload_users" should be on boarded to the app
#  And User "deonaraya4@gmail.com" logout

#  Scenario: Bulk user CSV update by Admin
#    Given the user is on Users tab on admin page
#    When he uploads a csv file "given_upload_users_update" to on board user "NEWUSER"
#    And the csv "given_upload_users_update" is updated for Organisation and title
#    And User "deonaraya4@gmail.com" logout

  @Admin
  Scenario: Manager Validation : User is his own manager in Bulk user CSV upload
    Given the user is on Users tab on admin page
    When he uploads a csv file "no_manager_assigned" to on board user "z"
    Then user in "no_manager_assigned" should be on boarded to the app with no manager assigned.
    And User "deonaraya4@gmail.com" logout


#  Scenario: Manager Validation : Manager is part of user on boarding in Bulk user CSV upload
#    Given the user is on "Users" tab on admin page
#    When he uploads a csv file "manager_assignment" to on board user "z"
#    Then user in "upload_users" should be on boarded to the app
#    And user "z" should be assigned a manager
#    And User "deonaraya4@gmail.com" logout

 # Scenario: Login functionality
  #  Given user navigates to Define Expertise Tab
   # And User "deonaraya1@gmail.com" logout

