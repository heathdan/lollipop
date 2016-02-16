@Functional
Feature: Admin
  As a b2b admin user
  I should be able to administer the system
  So that other users can use b2b application as desired


  Background:
    Given that the user "admin_name" logged in as "deonaraya1@gmail.com" and "Cisco@1234"


  Scenario: Creation of Custom Role and inherit System Admin role
    Given the user is on "Roles and Permissions" tab on admin page
    When he creates a new role named "CustomSystemAdminRole" and inherits permissions from "SystemAdmin" role
