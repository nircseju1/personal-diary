# Getting Started

### Prerequisite  
* IntelliJ IDEA
* MySQL Server

### Guideline to incorporate Database information
Steps to follow:
* Step-1: Get db-script file "personal_diary.sql" from "\personal-diary\dbScript" folder and execute to incorporate database structure
  
### Guideline to incorporate project into IntelliJ IDEA  
Steps to follow:

* Navigate "File > New > Project From Existing Sources" path
* Select project folder from local path
* Convert project as MAVEN Project (if not converted automatically)

### Guideline for WAR Build  
Steps to follow:

* Step-1: Open "pom.xml" file to change WAR version

* Step-2: Select "Maven Project" Tab from right side of panel

* Step-3: Double-Click on "clean" item under "Lifecycle" option 

* Step-4: Double-Click on "package" item under "Lifecycle" option.

* Step-5: Copy WAR file from "\personal-diary\target" folder and keep into "webapps" of tomcat directory

* Step-6: Rename WAR file as "ROOT.war"

* Step-7: Rund "Tomcat" to complete deployment process


### swagger
To view Api Documentation, swagger UI can be used:

* URL will look like as follows "http://localhost:8080/swagger-ui.html"
