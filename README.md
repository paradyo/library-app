# Library APP

This project was developed for education purposes. Feel free to use :)

## 🚀 Start using it

1. Open the project directory with your favorite editor. Wait for synchronization.
2. Intelij IDEA -> Preferences -> Build,Execution,Deployment -> Compiler -> Build project automatically
3. Intelij IDEA -> Preferences -> Advanced Settings -> Allow auto-make to start even if developed application is currently running
4. Intelij IDEA -> Run/Debug Configurations -> Modify options -> Add before launch task -> 1. Build Project -> 2. Build
5. Check src/main/java/resources/application.properties for these lines;
   - spring.devtools.restart.enabled=true
   - spring.devtools.restart.additional-paths=src/main/java
4. Then click Build & Run!

## 🧬 Folder structure

This is the structure of the files in the app tier:

```sh
    │
    ├── src
    │   ├── main
    │   │   ├── java
    │   │   │   ├── com.engineer.library
    │   │   │   │   ├── controller                          # Contains the Spring MVC controllers that handle HTTP requests.
    │   │   │   │   ├── model                               # Houses the data models or entities for your application.
    │   │   │   │   ├── service                             # Implements the business logic and acts as an intermediary between the controllers and repositories.
    │   │   │   │   ├── repository                          # Handles data access and interactions with the database.
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

## CI/CD

Will be here soon

## Deployment

Will be here soon

## ⚗️ Technologies list

- Spring Boot DevTools
- Spring Web
- Spring Security
- Validation
- Spring Data JPA
- Spring Boot Actuator

## ⚠️ Warning

Cloning this repo makes you better developer. Be careful!

## 📖 Learn More

- [My website: ](https://emrecan.co/) – Contact with me!
- [My Youtube channel](https://www.youtube.com/channel/UCHnhd6yOwxKyQTZU1yDqV0w) – Sharing my experience in whole my career.