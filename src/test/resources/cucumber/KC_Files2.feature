@KC
Feature: KC-1
  As a b2b admin user
  I should be able to view the document page
  So that I can able to upload document,edit document and share document

  Background:
    Given that the user logged in as "admin_user" and "password"

#  Scenario:Learner uploads a pdf file
#    Given the user is on "My File" KC page
#    Then the user should be able to upload file "pdf_UploadDoc" from "DataStageAndMongoDB.pdf"
#    When I open "My Activities" page
#    Then I should be able to see the "uploaded" a "file" document activity
#    And the user deletes the file "pdf_UploadDoc"
#    And User "admin_user" logout

#  Scenario:Learner uploads a video file
#    Given the user is on "My File" KC page
#    Then the user should be able to upload file "Video_File" from "small.webm"
#    And the user deletes the file "Video_File"
#    And User "admin_user" logout

#  Scenario:Learner uploads an epub file
#    Given the user is on "My File" KC page
#    Then the user should be able to upload file "epub_UploadDoc" from "Unilever.epub"
#    And the user deletes the file "epub_UploadDoc"
#    And User "admin_user" logout

#  Scenario:Rate a file
#    Given the user is on "My File" KC page
#    Then the user should be able to upload file "ppt_UploadDoc" from "MongoDB_2.ppt"
#    When the user gives a rating for document
#    Then should be able to see the updated rating in document page
#    And the user deletes the file "ppt_UploadDoc"
#    And User "admin_user" logout

  Scenario: Share a file with other user
    Given the user is on "My File" KC page
    Then the user should be able to upload file "pdf_UploadDoc" from "DataStageAndMongoDB.pdf"
    When he shares it with "learner_displayname"
    And User "admin_user" logout
    Given that the learner_user logged in as "learner_user" and "password"
    Then the user "learner_username" should be able to view sharedFile
    And User "learner_username" logout
    Given that the user logged in as "admin_user" and "password"
    And the user deletes the file "pdf_UploadDoc"
    And User "admin_user" logout

  Scenario:Edit the file metadata
    Given the user is on "My File" KC page
    Then the user should be able to upload file "Edit_metadata" from "Intro_to_MongoDB.ppt"
    Then the user should be able to edit document with "Test_Document" and "sample" tags
    When I open "My Activities" page
    Then I should be able to see the "uploaded" a "file" document activity
    And the user deletes the file "Test_Document"
    And User "admin_user" logout