@KC
Feature: KC-1
  As a b2b admin user
  I should be able to view the document page
  So that I can able to upload document,edit document and share document

  Background:
    Given that the user "admin_name" logged in as "admin_user" and "password"

  Scenario:Learner uploads a pdf file
    Given the user is on "My File" KC page
    Then the user should be able to upload file "pdf_UploadDoc" from "DataStageAndMongoDB.pdf"
#    When I open "My Activities" page
#    Then I should be able to see the "uploaded" a "file" document activity
#    And the user deletes the file "pdf_UploadDoc"