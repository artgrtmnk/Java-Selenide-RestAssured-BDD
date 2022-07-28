# Java-Selenide-RestAssured-BDD


### Installation
1. Install [Java 8](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html)
2. Export JAVA_HOME to environment [Link for Mac](https://stackoverflow.com/questions/15826202/where-is-java-installed-on-mac-os-x)
3. Install [Maven](https://maven.apache.org/install.html)
4. Download the project.
5. Install maven dependencies in pom.xml file.
6. Get your token for [GoRest](https://gorest.co.in/my-account/access-tokens) (You need to register/login first).
7. In the project's root folder, paste your token into `token.json` file as a value for the `token` key.
8. Optionally: You can install `Cucumber` plugIn for Idea. It allows you to start tests separately from IDE.

### Running tests
1. You can run tests using BaseTest file in the `src/test/java/tests` folder.
2. As well, you can do it using features files in the `src/test/java/features`.
3. And you can also do it with command line with a single command `mvn test`.
