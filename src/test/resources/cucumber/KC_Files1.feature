Feature: KC-1
  As a b2b admin user
  I should be able to view the document page
  So that I can able to upload document,edit document and share document

  Background:
    Given that the user "admin_name" logged in as "admin_user" and "password"


  Scenario:Rate a file
    Given the user should be able to upload file "ppt_UploadDoc" from "Documents/MongoDB_2.ppt"
    When the user gives a rating for document
    Then should be able to see the updated rating in document page
    And the user deletes the file "ppt_UploadDoc"

  Scenario:Like,Favourite and Follow a file
    Given the user should be able to upload file "docx_UploadDoc" from "Documents/document.docx"
    When the user wants to Follow,Like,Favorite a document
    Then user should be able to see the document in followed,liked,favorited
    When the user does Un-Like,Undo Favorite,Un-Follow actions
    Then user should not be able to see the document in followed,liked,favorited
    And the user deletes the file "docx_UploadDoc"

  Scenario:Verify the other file type is supporting
    Then user is verify the other file type "Documents/test doc.txt" is not supporting

  Scenario: Share a file with other user
    Given the user should be able to upload file "pdf_UploadDoc" from "Documents/DataStageAndMongoDB.pdf"
    When he shares it with "learner_displayname"
    Then the user "learner_displayname" with username "learner_username" and password "learner_password" should be able view it
    And logout and login as user "admin_displayname" with username "admin_username" with password "admin_password"
    And the user deletes the file "pdf_UploadDoc"

  Scenario: File title Quoted search in unified search
    Given the user should be able to upload file "quotedsearch" from "Documents/pyramid_marketing.epub"
    When the user searches for that document in unified search with double quotes
    And the user deletes the file "quotedsearch"

  Scenario:Edit the file metadata
    Given that the user "admin_displayname" logged in as "admin_username" and "admin_password"
    When the user should be able to upload file "Edit_metadata" from "Documents/Intro to MongoDB.ppt"
    Then the user should be able to edit document with "Test Document" and "sample" tags
    When I open "My Activities" page
    Then I should be able to see the "edited" a "file" document activity
    And the user deletes the file "Test Document"

  Scenario: Filter by file Type Epub
    When the user filter by "documentType" called "EPUB"
    Then user should be able to see the list of "EPUB" documents

  Scenario: Filter by file Type PDF
    When the user filter by "documentType" called "PDF"
    Then user should be able to see the list of "PDF" documents

  Scenario: Sort file name in ascending order
    Given that user is in "My Files" page
    When user sorts by "Name" in "ascending" order
    Then files should be sorted by "Name" in "ascending" order

  Scenario: Sort file name in descending order
    Given that user is in "My Files" page
    When user sorts by "Name" in "descending" order
    Then files should be sorted by "Name" in "descending" order
