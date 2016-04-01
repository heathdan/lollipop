Feature: KC-1
  As a b2b admin user
  I should be able to view the document page
  So that I can able to upload document,edit document and share document

  Background:
    Given that the user "admin_name" logged in as "admin_user" and "password"




  Scenario:Like,Favourite and Follow a file
    Given the user should be able to upload file "docx_UploadDoc" from "Documents/document.docx"
    When the user wants to Follow,Like,Favorite a document
    Then user should be able to see the document in followed,liked,favorited
    When the user does Un-Like,Undo Favorite,Un-Follow actions
    Then user should not be able to see the document in followed,liked,favorited
    And the user deletes the file "docx_UploadDoc"

  Scenario:Verify the other file type is supporting
    Then user is verify the other file type "Documents/test doc.txt" is not supporting

  Scenario: File title Quoted search in unified search
    Given the user should be able to upload file "quotedsearch" from "Documents/pyramid_marketing.epub"
    When the user searches for that document in unified search with double quotes
    And the user deletes the file "quotedsearch"


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
