# SpringMavenMVCProject-- <strong>FamousHikesAroundTheWorldMavenProject</strong>


A demo Maven Project using the following technologies;
- Spring Java Framework
- Spring Security
- Hibernate Framework
- JSP
- Bootstrap
- XML

This app allows the users to view all the famous hiking trails around the world. Basic information about each hike trail is provided on browsing individual record. Also, users have chance to add review for trails but the users must be logged in to complete this. If the user is not logged in then they will be automatically re-directed to login page. 

Users can register with the link on the header section(plus login and logout can be done with appropriate links). User authentication is carried out using Spring Security and DAO authentication is done to verify users credentials are correct. For admin users, additional text "[admin]" appears after the name(on the header) to highlight the logged user has Admin role. Passwords are encrypted(using BCryptPasswordEncoder) provided by spring framework. Forms(for entities in package com.project.hikes.entity) are validated using javax validation, plus using interfaces(ConstraintValidator, Validator). 

Guests can check any hike trails page. But only users with membership are able to add the reviews for hike trails. And only admins can perform CRUD operation on Hike trail records. Appropriate links will appear to carry out CRUD operation for admins. 

On starting, only few recently added hike trails are shown on Homepage. And the trails list will show all the available hike trails from the database. On clicking the thumbnail image from the available choices, anyone can read the trail information. "Add Review" button is displayed for all(but requires membership to complete this). All the reviews(from several users) for the particular trail record are listed according to the date(in descending manner). Users can only delete their reviews in the future as well.

A live demo of this app is hosted on Heroku and can be found at https://famous-hikes-around-the-world.herokuapp.com/


