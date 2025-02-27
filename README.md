# Bank Kata

## Description
Bank Kata is a simple banking application that allows users to perform basic transactions such as deposits, withdrawals, and viewing their account statements. The project follows clean architecture principles and is designed with testability in mind.

## Features
- Deposit money into an account
- Withdraw money from an account with balance validation
- Print a statement of all transactions
- Fully tested using JUnit and Mockito
- Code quality analysis with SonarQube
- Code coverage measurement with JaCoCo

## Technologies Used
- Java 17
- Maven
- JUnit 5
- Mockito
- SonarQube
- JaCoCo

## Prerequisites
Ensure you have the following installed:
- Java 17+
- Maven 3+
- Git
- Docker (for running SonarQube locally)

## Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/MBAREK0/Bank-Kata.git
   ```
2. Navigate to the project directory:
   ```sh
   cd Bank-Kata
   ```
3. Build the project:
   ```sh
   mvn clean install
   ```

## Running Tests
To run the unit tests, use:
```sh
mvn test
```

## Running SonarQube Analysis
1. Start SonarQube using Docker:
   ```sh
      docker compose -f src/main/docker/sonarqube.yaml up -d
   ```
2. Run the SonarQube analysis:
   ```sh
   mvn clean verify sonar:sonar 
      -Dsonar.projectKey='your-project-key-at-sonarqube'   
      -Dsonar.projectName='your-project-name-at-sonarqube'  
      -Dsonar.host.url=http://localhost:9000   
      -Dsonar.token=your-sonar-token

   ```

## Running JaCoCo for Test Coverage
To generate a test coverage report using JaCoCo, run:
```sh
mvn clean verify
```
The JaCoCo report will be available at:
```
target/site/jacoco/index.html
```
You can open this file in a browser to view the coverage report.

## Project Structure
```
Bank-Kata/
│── src/
│   ├── main/
│   │   ├── java/org/mbarek0/bank_kata/
│   │   │   ├── entity/
│   │   │   │   ├── Transaction.java
│   │   │   ├── util/
│   │   │   │   ├── Clock.java
│   │   │   │   ├── StatementPrinter.java
│   │   │   ├── repository/
│   │   │   │   ├── TransactionRepository.java
│   │   │   ├── service/
│   │   │   │   ├── impl/
│   │   │   │   │   ├── Account.java
│   │   │   │   │   ├── AccountService.java
│   │   │   ├── BankKataApplication.java
│   │   ├── resources/
│   │   │   ├── application.yaml
│   ├── test/
│   │   ├── java/org/mbarek0/bank_kata/
│   │   │   ├── repository/
│   │   │   │   ├── TransactionRepositoryTest.java
│   │   │   ├── service/
│   │   │   │   ├── AccountTest.java
│   │   │   ├── BankKataApplicationTests.java
│── pom.xml
│── README.md
│── Dockerfile
│── .gitignore
│── mvnw
│── mvnw.cmd
│── LICENSE
```

## Usage Example
```java
Account account = new Account();
account.deposit(1000);
account.withdraw(500);
account.printStatement();
```

## Author
[M'barek El Aadraoui](https://github.com/MBAREK0)
