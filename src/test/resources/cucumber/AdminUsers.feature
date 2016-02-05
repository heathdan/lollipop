@Functional
Feature: Admin
  As a b2b admin user
  I should be able to administer the system
  So that other users can use b2b application as desired


  Background:
  Given that the user "admin_name" logged in as "deonaraya1@gmail.com" and "Cisco@1234"


  Scenario: When user logs out
  Given user "deonaraya1@gmail.com" logout

