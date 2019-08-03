# Sofascore Spring

The goal of this project will be to download the data from the website https://sofascore.com, and finally create an algorithm to guest what it's the best score to bet

# Requirement
- [JAVA 12](https://www.oracle.com/technetwork/java/javase/downloads/jdk12-downloads-5295953.html)
- Kotlin 1.3.30
- Maven 4.0.0
- [An IDE like intellij using Spring boot](https://www.jetbrains.com/help/idea/spring-boot.html)

# Installation
- Clone the project and open it into your IDE
- Reimport maven (right click on pom.xml -> Maven -> Reimport)

# How to use
- Go to Sofascore website and get the exact name of a Football tournament (ex: Ligue 1)
- Open your brower with the url : http://localhost:8080/football/event/season/tournament/<Exact name of the tournament>/period/<Number of days from the current day you want to load>

