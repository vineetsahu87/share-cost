# Share Cost 
#### Author Vineet Sahu
A single person tends to pay a bill (at a restaurant, etc) when they go out in a group and they
expect to settle the sharing later.

When a group of people plan for a trip or similar multiple people spend on multiple items the cost of which needs to be shared later.In such scenarios tracking of settlements becomes a challenge.

The project tries to solve these problems by providing restful API and models for sharing cost between a group. The restful API which are using
the HTTP protocol enables the user to access it from Desktop, Tab, Mobile, vertially any device using the HTTP protocol.

## How is the project Organized ?
## Installation
  Following are the minimum things required for installation of the project
  - Java 1.8
  - [Maven 3.6.1](https://maven.apache.org/download.cgi?Preferred=ftp://mirror.reverse.net/pub/apache/)
  - [Tomcat 9](https://tomcat.apache.org/download-90.cgi)
  - [MongoDB](https://www.mongodb.com/download-center/community)
  
  Once the MongoDB is installed make sure it is running on the following HOST:PORT localhost:27017.
  
  To build the project run the following command.
  ```sh
  mvn clean install
  ```
  This would build all the projects in the multi module project. 
  
  From `\share-cost\share-cost-rest\target` get the war file to be deployed in the Tomcat server. The tomcat server console can be used 
  to deploy the war or the 
## Architecture of the software
## APIs Exposed
## ToDo List
- Unit Test - Could not complete unti tests due vastness of the problem and time crunch.The maven plugin to show the test reports has been
incorporated.
- POM clean up - The POM needs to be cleaned up to have the depdencies in the root level pom.
- Making the host and port for MongoDB Configrable instead of hard coding in the file.
- Error Handling - Improvement in the error handling can be done.
- JavaDoc - Improvement in the javadoc for all the classes. JavaDoc for the main classes is completed.
## Future Enhacements
- API documentation using Swagger for easy of communication with the stakeholder. Ideally the YAML file should be created before the 
development is started but due to time crunch had to skip this step.
- OAuth needs to be implemented for the project. Current it does not have any authentication.
- An UI which can help the user visualize the expenses in a real time for any event.
- An Mobile APP which can help the user add, view, update the expenses for an event on the go.
- Implement inmemory caching to further improve the performance of the system. In Memory database such as **REDIS** can help improve the 
performance of our queries.
