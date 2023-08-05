# 🤠 Library APP 🤠

This project was developed for education purposes. Feel free to use :)

## 🚀 Start using it

1. Open the project directory with your favorite editor. Wait for synchronization.
2. Intelij IDEA -> Preferences -> Build,Execution,Deployment -> Compiler -> Build project automatically
3. Intelij IDEA -> Preferences -> Advanced Settings -> Allow auto-make to start even if developed application is currently running
4. Intelij IDEA -> Run/Debug Configurations -> Modify options -> Add before launch task -> 1. Build Project -> 2. Build
5. Check src/main/java/resources/application.properties for these lines;
   - spring.devtools.restart.enabled=true
   - spring.devtools.restart.additional-paths=src/main/java
6. Install MySQL via Docker!
   - ```docker run --detach --env MYSQL_ROOT_PASSWORD=testpass --env MYSQL_USER=testuser --env MYSQL_PASSWORD=testpass --env MYSQL_DATABASE=library --name mysql --publish 3306:3306 mysql:8-oracle```
7. Then click Build & Run!

NOTE: Every mock data's password is 'testpass'.

## 🥸 REST API Documentation

[Click here for REST API docs and design.](https://documenter.getpostman.com/view/12550271/2s9XxyRDBY)

## 🧬 Folder structure

This is the structure of the files in the app tier:

```sh
    │
    ├── src
    │   ├── main
    │   │   ├── java
    │   │   │   ├── com.engineer.library
    │   │   │   │   ├── configuration                       # Contains the Spring configuration files.
    │   │   │   │   ├── constant                            # Contains the constant values.
    │   │   │   │   ├── controller                          # Contains the Spring MVC controllers that handle HTTP requests.
    │   │   │   │   ├── exception                           # Contains the custom exception files.
    │   │   │   │   ├── model                               # Houses the data models or entities for your application.
    │   │   │   │   ├── repository                          # Handles data access and interactions with the database.
    │   │   │   │   ├── service                             # Contains the business logic, DTO and other things related to any service.
    │   │   │   │   ├── LibraryApplication.java             # SpringBootApplication configuration file.
    │   │   │   ├── resources
    │   │   │   │   ├── static                              # Project-wide static files.
    │   │   │   │   ├── templates                           # Project-wide templates.
    │   │   │   │   ├── application.properties              # Project-specific configurations.
    │   ├── test
    │   │   ├── java
    │   │   │   ├── com.engineer.library                    # Contains test-related files and classes.
    ├── mvnw
    ├── mvnw.cmd
    ├── .gitignore
    ├── pom.xml
    ├── README.md
```

## 🤕 Performance & Load Testing

1. Go to [Apache JMeter](https://dev.mysql.com/doc/connector-j/8.1/en/connector-j-installing-maven.html) page then download the binary .zip file.
2. Extract the zip file then inside the folder you'll see the bin folder.
3. Open the bin folder in Terminal and run this command to open GUI.
   - ```sh jmeter.sh```
4. Then in the JMeter GUI open Options -> Themes and choose System.
5. Start the application.
6. Right-click TestPlan in the left on the GUI -> Go to Threads (Users) -> setUp Thread Group.
7. Then give it a name and enter the number of users you want to test the endpoint.
8. Right-click the Thread Group -> Add -> Sampler -> HTTP Request
9. Give it a name and enter the host name (which is localhost for local) and port name.
10. Choose method  and enter path.
11. Right-click the HTTP Request -> Add -> Config Element -> HTTP Header Manager (You can additionally add HTTP Authorization Manager to test authentication needed paths or methods.)
12. Click Add and enter Name : Api-Version and Value : v1.0 
13. Right-click Thread Group -> Add -> Listener -> View Result Tree
14. Click Save on top of the GUI.
15. Click HTTP Request you want to test. Click Run icon on top of the GUI.
16. Click your Result Tree and you will see the results on the left. Click and see the load times and latencies (These are the most important ones.)

As you can see testing results are perfect because of the REST API Caching mechanism.
![performance_testing_1](https://github.com/paradyo/library-app/blob/main/readme_photos/performance_testing_1.png)
![performance_testing_2](https://github.com/paradyo/library-app/blob/main/readme_photos/performance_testing_2.png)



## 🤕 CI/CD

Will be here soon

## 😴 Deployment

Will be here soon

## 😴 TODO

1. User Mockito REST API tests.
2. Book unit tests.
3. GuestBook unit and Mockito tests.

## ⚗️ Technologies list

- Spring Boot DevTools
- Spring Web
- Spring Security
- Validation
- Spring Data JPA
- Spring Boot Actuator
- [MySQL Connector J](https://dev.mysql.com/doc/connector-j/8.1/en/connector-j-installing-maven.html)

## ⚠️ Warning

Cloning this repo makes you better developer. Be careful!

## 📖 Learn More

- [My website: ](https://emrecan.co/) – Contact with me!
- [My Youtube channel](https://www.youtube.com/channel/UCHnhd6yOwxKyQTZU1yDqV0w) – Sharing my experience in whole my career.