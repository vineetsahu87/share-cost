# Share Cost 
#### Author Vineet Sahu
A single person tends to pay a bill (at a restaurant, etc) when they go out in a group and they
expect to settle the sharing later.

When a group of people plan for a trip or similar multiple people spend on multiple items the cost of which needs to be shared later.In such scenarios tracking of settlements becomes a challenge.

The project tries to solve these problems by providing restful API and models for sharing cost between a group. The restful API which are using
the HTTP protocol enables the user to access it from Desktop, Tab, Mobile, vertially any device using the HTTP protocol.

## How is the project Organized ?
The project is a maven multi-module project which contains 4 modules
- **share-cost-domain-model** - Contains the domain model for the cost sharing APP.

| Domain Objects | Description|
| ------ | ------ |
| **User** | The class store the user related information like firstname, lastname, emailaddress. |
| **Group** | The class store the group information. It has the group name and the users which are part of the group. Eg. "Acounting Team" can create a group with all the members of the accouting team.  |
| **Event** | The class has the name and the description of the event. The event would have a associated group. Eg. If the "Accounting Team has done a Team outing then they can crete a "Team Outing" event and associate the "Accounting Team" with the event.  |
| **Expense** | The class stores all the expenses for the outing and who paid thoses expenses. |
| **Ledger** | The class stores the book of record of who owes whom. |

- **share-cost-rest** - It is a Jersey based web service project. The project exposes the API required for cost sharing.
- **share-cost-service** - The project defines the services required for the cost sharing app. The abstraction of the service would help us in case implementation needs to be replaced (in case we want to go with another DB) or the implemenation remains the same but the rest layer needs to be changed. There is no tight coupling between the share-cost-service and share-cost-service-ml. **ServiceLocator** is used to identify the service implemtation.
- **share-cost-service-ml** - The projects contains the implementation for the service. The project is responsible for opening the connection with the DB, Doing CRUD operation on the DB, Closing the DB Connections.

## Architecture of the software
Following are some of the Key decisions taken while designing the software
- **Restful Web Service** - The project use Restful Web Services to expose API for cost sharing. Since Restful web services use HTTP protocol to communicate it can be used with any device.
- **Apache Tomcat** - Apache tomcat was used locally to host the web application. Any other server can be used to host the Application as well.
- **Mongo DB** - There were multiple reasons for selecting MongoDB. Since MongoDB is a NoSQL document database it is faster compared to SQL. It would help us scale the application once the number of users increase. The number of reads and writes to the application would be more that the updates. The reads and writes are very fast in MongoDB.

## Installation
  Following are the minimum things required for installation of the project
  - Java 1.8
  - [Maven 3.6.1](https://maven.apache.org/download.cgi?Preferred=ftp://mirror.reverse.net/pub/apache/)
  - [Apache Tomcat 9](https://tomcat.apache.org/download-90.cgi)
  - [MongoDB](https://www.mongodb.com/download-center/community)
  
  #### DB Configuration
  Once the MongoDB is installed make sure it is running on the following HOST:PORT localhost:27017. The application would create a
  new DB or collections in case none exists which would be the case the first time.
  
  #### Building the project
  To build the project run the following command.
  ```sh
  mvn clean install
  ```
  This would build all the projects in the multi module project. 
  
  #### Deploying the WAR to Tomcat
  From `\share-cost\share-cost-rest\target` get the war file to be deployed in the Tomcat server. The tomcat server console can be used 
  to deploy the war or the Eclipse servers plugin can help manage the deployment of war to the tomcat server (which I used during  development). By default the services uri would be 
  ```
  http://localhost:8080/share-cost-rest/webapi/
  ```
  The user would require to append the below URI Path.
  
## APIs Exposed

| Context | Operation| URI Path | Parameters |Description|
| ------ | ------ | ------ | ------ | ------ |
| **User** | POST | /users | | Creates a new user.| 
| **User** | GET | /users | *QueryParam* - emailAddress | Gets the users deatils based on the emailAddress provided in the query param|
| **Group** | POST | /group |  | Creates a new group.|
| **Group** | GET | /group/{groupId} | | Gets the group given the groupId.|
| **Group** | POST | /group/{groupId}/user | | Adds a new user to the Group.|
| **Group** | GET | /group/user/{userId} | | Gets the groups that the person is associated to.|
## ToDo List
- Unit Test - Could not complete unti tests due vastness of the problem and time crunch.The maven plugin to show the test reports has been
incorporated.
- POM clean up - The POM needs to be cleaned up to have the depdencies in the root level pom.
- Making the host and port for MongoDB Configrable instead of hard coding in the file.
- Error Handling - Improvement in the error handling can be done.
- JavaDoc - Improvement in the javadoc for all the classes. JavaDoc for the main classes is completed.
- Service & DAO refactoring - The logic from the DAO layer needs to be moved to the Service to make sure DAO is just pulling the data from the DB and nothing else.

## Future Enhacements
- **Swagger Documentation** -API documentation using Swagger for easy of communication with the stakeholder. Ideally the YAML file should be created before the development is started but due to time crunch had to skip this step.
- **Versioning** - API versioning needs to be done which can help us maintain the API better. For that the version needs to be incorporated in the URI.
- **OAuth** - OAuth needs to be implemented for the project. Current it does not have any authentication.
- **User Registration** - Provide UI pages and services for user registration. 
- **Webpage** - An UI which can help the user visualize the expenses in a real time for any event. It can be done in Angular JS or React which provide rich functionalities.
- **Mobile Application** - An Mobile APP which can help the user add, view, update the expenses for an event on the go.
- **Performance Scaling** - Implement inmemory caching to further improve the performance of the system. In Memory database such as **REDIS** can help improve the performance of our queries.
- **Horizontal Scaling** - Would require to host the web services in multiple servers. A Load Balancer needs to be added in front of the server to distribute the load among the servers running web services
- **DDOS Attach prevention** - Implement hand shake for Client to Load Balancer once completed the request would be send to the web server.
- **DB Replication** - The DB needs to be replicated in multiple machines so that we do not have single point of failure.
