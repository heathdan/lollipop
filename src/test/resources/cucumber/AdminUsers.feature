@Admin
Feature: Admin
  As a b2b admin user
  I should be able to administer the system
  So that other users can use b2b application as desired

  Background:
    Given that the user logged in as "admin_user" and "password"

 Scenario: Creation of Custom Role and inherit System Admin role
   Given the user is on Roles and Permissions tab on admin page
   When he creates a new role named "CustomSystemAdminRole" and inherits permissions from "SystemAdmin" role
   And user navigates to the Users tab
   When he assigns role "CustomSystemAdminRole" to "learner_user"
   And User "admin_user" logout
   Given that the learner_user logged in as "learner_user" and "password"
   Then user should be assigned "Licensing" permission under "System"
   And user should be assigned "Define Expertise" permission under "User"
   Then delete "CustomSystemAdminRole" role created
   And User "learner_user" logout

#  Scenario: Creation of Custom Role and add permissions to role
#    Given the user is on Roles and Permissions tab on admin page
#    When he creates a new role named "CustomAutomationRole"
#    And adds permissions "View License" to "CustomAutomationRole" role
#    When he assigns role "CustomAutomationRole" to "learner_user"
#    And User "admin_user" logout
#    Given that the learner_user logged in as "learner_user" and "password"
#    Then user should be assigned "View License" permission under "CustomAutomationRole" role
#    And "learner_username" should not be assigned "Add/Delete/Upload Expertise" permission on "User" page
#    And User "learner_user" logout
#    Given that the learner_user logged in as "learner_user" and "password"
#    Then delete "CustomAutomationRole" role created

  Scenario: Bulk user CSV upload by Admin
    Given the user is on Users tab on admin page
    When he uploads a csv file "upload_users" to on board user "Z"
    Then user in "upload_users" should be on boarded to the app
    And User "admin_user" logout

  Scenario: Bulk user CSV update by Admin
    Given the user is on Users tab on admin page
    When he uploads a csv file "given_upload_users_update" to on board user "NEWUSER"
    And the csv "given_upload_users_update" is updated for Organisation and title
    And User "admin_user" logout

  Scenario: Manager Validation : Manager is part of user on boarding in Bulk user CSV upload
    Given the user is on Users tab on admin page
    When he uploads a csv file "manager_assignment" to on board user "z"
    Then user in "manager_assignment" should be on boarded to the app with manager assigned
    And User "admin_user" logout

  Scenario: Manager Validation : User is his own manager in Bulk user CSV upload
    Given the user is on Users tab on admin page
    When he uploads a csv file "no_manager_assigned" to on board user "z"
    Then user in "no_manager_assigned" should be on boarded to the app with no manager assigned.
    And User "admin_user" logout


