# M2-P03: File Upload and Download Automation

## Project structure
- `pom.xml` — Maven configuration and dependencies.
- `src/main/java/com/college/stqa/Main.java` — offline demonstration, runnable without Selenium/browser dependencies.
- `src/main/java/SeleniumPractical.java` — Selenium-based practical implementation (and POI/Log4j/TestNG where applicable).
- `target/` — build output and reports after Maven commands.

## Run the included standalone JAR
The JAR in `jar/` is an **offline demonstration** of the practical's core checks. It does not drive a browser. From this folder run:
```bash
java -jar jar/M2-P03-demo.jar
```

## Build the Maven project
Install JDK 17+ and Maven 3.9+, then run:
```bash
mvn clean package
```
This resolves dependencies from Maven Central and creates `target/module2_practical03_fileuploaddownloadautomation-1.0.0.jar`.

## Run browser automation
Configure Chrome/Firefox/Edge and their matching drivers. For Grid practicals start Selenium Grid and pass `-Dgrid.url=http://localhost:4444`.
For a custom target URL use `-Dtest.url=https://your-test-site.example`.
The browser example source is a template: update locators and expected content for your assigned website before submission. Selenium browser tests require internet/browser access and may need browser driver setup.

## Notes
- The JAR is compiled locally from the dependency-free `Main.java` because Maven is not installed in the build environment.
- The Maven project includes the Selenium implementation and dependencies; rebuild it with Maven for a full dependency-resolved project JAR.
