Feature: Admin
  As a b2b admin user
  I should be able to administer the system
  So that other users can use b2b application as desired

  @Jenkins_Users
  Scenario: Login for Admin tests
    Given that the user "" logged in as "" and ""

  @Jenkins_Users
  Scenario: Creation of Custom Role and inherit System Admin role
    Given the user is on "Roles and Permissions" tab on admin page
    When he creates a new role named "CustomSystemAdminRole" and inherits permissions from "SystemAdmin" role
    When he assigns role "CustomSystemAdminRole" to "learner_username"
    And the user "learner_displayname" with username "learner_username" and password "learner_password" logs in to the system
    Then he should get "Admin"
    And "learner_username" should be assigned "View License" permission under "CustomSystemAdminRole" role
    And "learner_username" should be assigned "Add/Delete/Upload Expertise" permission under "CustomSystemAdminRole" role
    And "learner_username" should be assigned "Expertise Assignment" permission under "CustomSystemAdminRole" role
    Then delete "CustomSystemAdminRole" role created