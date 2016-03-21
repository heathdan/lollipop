@Knowledge_Center
Feature: Documents
  As a b2b user
  I should be able to view the document page
  So that I can able to upload document,edit document and share document

  Background:
    Given that the user "admin_name" logged in as "admin_user" and "password"

  Scenario: Learner uploads a pdf file
    Given User "admin_user" logout
    And that the user "learner" logged in as "learner_user" and "password"
    Then user navigates to "My Files" page