# 🧪 Test Automation Framework

A robust, scalable, and data-driven test automation framework built with **Java 11** and **TestNG**, supporting local browser execution, headless mode, and cloud-based testing via **LambdaTest**.

---

## 📋 Table of Contents

- [Framework Overview](#framework-overview)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Setup & Installation](#setup--installation)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
  - [Local Execution](#local-execution)
  - [Headless Mode](#headless-mode)
  - [LambdaTest Cloud Execution](#lambdatest-cloud-execution)
- [Data-Driven Testing](#data-driven-testing)
- [Test Reports](#test-reports)
- [Logs](#logs)
- [Framework Features](#framework-features)

---

## Framework Overview

This framework is designed to automate web application testing with a clean separation of concerns — test data, test logic, configuration, and reporting are all independently managed. It supports multiple execution modes (local, headless, cloud) configurable entirely from the command line without modifying source code.

---

## Tech Stack

| Category             | Technology / Library         | Version     |
|----------------------|------------------------------|-------------|
| Language             | Java                         | 11          |
| Test Runner          | TestNG                       | Latest      |
| Build Tool           | Maven                        | 3.6+        |
| Browser Automation   | Selenium WebDriver           | Latest      |
| Cloud Testing        | LambdaTest                   | —           |
| Data-Driven (CSV)    | OpenCSV                      | Latest      |
| Data-Driven (JSON)   | Gson                         | Latest      |
| Data-Driven (Excel)  | Apache POI                   | Latest      |
| Fake Data            | JavaFaker                    | Latest      |
| Reporting            | ExtentReports                | Latest      |
| Logging              | Log4j                        | Latest      |
| CLI Test Execution   | Maven Surefire Plugin        | Latest      |

---

## Prerequisites

Ensure the following are installed and configured on your machine before running the framework:

1. **Java 11** — [Download JDK 11](https://adoptium.net/)
   ```bash
   java -version
   # Expected: openjdk version "11.x.x"
   ```

2. **Maven 3.6+** — [Download Maven](https://maven.apache.org/download.cgi)
   ```bash
   mvn -version
   # Expected: Apache Maven 3.x.x
   ```

3. **Browser Drivers** (for local execution)
   - ChromeDriver — must match your installed Chrome version
   - GeckoDriver — for Firefox
   - Ensure drivers are on your system `PATH` or configured in the framework

4. **LambdaTest Account** (for cloud execution only)
   - Sign up at [LambdaTest](https://www.lambdatest.com/)
   - Obtain your **Username** and **Access Key** from your LambdaTest dashboard

---

## Project Structure

```
├── src
│   ├── main
│   │   └── java
│   │       └── com.framework
│   │           ├── base/              # Base test class & WebDriver setup
│   │           ├── config/            # Configuration readers & constants
│   │           ├── pages/             # Page Object Model (POM) classes
│   │           └── utils/             # Utility helpers (data readers, faker, etc.)
│   └── test
│       └── java
│           └── com.framework
│               └── tests/             # TestNG test classes
├── src/test/resources
│   ├── testdata/
│   │   ├── *.csv                      # CSV test data files
│   │   ├── *.json                     # JSON test data files
│   │   └── *.xlsx                     # Excel test data files
│   ├── testng.xml                     # TestNG suite configuration
│   └── log4j.properties               # Log4j logging configuration
├── reports/
│   └── reports.html                   # ExtentReports output (generated at runtime)
├── logs/
│   └── *.log                          # Log4j log files (generated at runtime)
├── pom.xml                            # Maven project descriptor
└── README.md
```

---

## Setup & Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/nikhilt1206/UI-Automation-Framework
   cd UI-Automation-Framework
   ```

2. **Install dependencies**
   ```bash
   mvn clean install -DskipTests
   ```

3. **Running Test on LambdaTest** (only needed for cloud execution)

   Set the following as environment variables:
   ```bash
   mvn clean test -Dbrowser=chrome -DisLambdaTest=true -DisHeadless=false -X
   ```
4. **Running Tests on Chrome browser on local Machine in Headless Mode**
   ```bash
   mvn clean install -DskipTests
   ```

---

## Configuration

Test execution is controlled by **three CLI parameters** passed via Maven. These eliminate the need to modify any source files between runs.

| Parameter      | Description                                      | Accepted Values          | Default  |
|----------------|--------------------------------------------------|--------------------------|----------|
| `browser`      | Browser to use for test execution                | `chrome`, `firefox`, `edge` | `chrome` |
| `isLambdaTest` | Whether to run tests on LambdaTest cloud         | `true`, `false`          | `false`  |
| `isHeadless`   | Whether to run the browser in headless mode      | `true`, `false`          | `false`  |

These parameters are passed using `-D` flags with the Maven Surefire Plugin, as shown in the examples below.

---

## Running Tests

### Local Execution

Run tests on your local machine using a visible browser window:

```bash
# Run with Chrome (default)
mvn clean test -Dbrowser=chrome -DisLambdaTest=false -DisHeadless=false

# Run with Firefox
mvn clean test -Dbrowser=firefox -DisLambdaTest=false -DisHeadless=false

# Run with Edge
mvn clean test -Dbrowser=edge -DisLambdaTest=false -DisHeadless=false
```

---

### Headless Mode

Headless mode runs the browser without a visible UI — ideal for CI/CD pipelines or faster local execution:

```bash
# Headless Chrome
mvn clean test -Dbrowser=chrome -DisLambdaTest=false -DisHeadless=true

# Headless Firefox
mvn clean test -Dbrowser=firefox -DisLambdaTest=false -DisHeadless=true
```

> 💡 Headless mode significantly reduces execution time and resource usage, making it well-suited for automated pipeline runs.

---

### LambdaTest Cloud Execution

Run tests on LambdaTest's real browser cloud infrastructure. Ensure your `LT_USERNAME` and `LT_ACCESS_KEY` environment variables are set before running.

```bash
# Run on LambdaTest with Chrome
mvn clean test -Dbrowser=chrome -DisLambdaTest=true -DisHeadless=false

# Run on LambdaTest with Firefox in headless mode
mvn clean test -Dbrowser=firefox -DisLambdaTest=true -DisHeadless=true
```

> 📌 When `isLambdaTest=true`, the framework automatically connects to the LambdaTest remote WebDriver hub using your credentials and the desired capabilities configured in the framework.

You can monitor live test execution and view session videos, screenshots, and logs on your [LambdaTest Dashboard](https://automation.lambdatest.com/).

---

## Data-Driven Testing

The framework supports three data formats for data-driven test scenarios:

### CSV (via OpenCSV)
- Place `.csv` files under `src/test/resources/testdata/`
- The CSV utility reads rows and maps them to test parameters automatically
- Useful for simple tabular test data (login credentials, form inputs, etc.)

### JSON (via Gson)
- Place `.json` files under `src/test/resources/testdata/`
- JSON objects are deserialized into POJOs using Gson
- Ideal for nested or complex test data structures

### Excel (via Apache POI)
- Place `.xlsx` files under `src/test/resources/testdata/`
- The POI utility reads sheets and rows, supporting multi-sheet test data
- Well-suited for test cases maintained by non-technical stakeholders

### Fake Data (via JavaFaker)
- The framework integrates **JavaFaker** to generate realistic random test data at runtime
- Used for fields like names, emails, phone numbers, addresses, and more
- Eliminates the need to hardcode repetitive test data for registration or form-filling scenarios

**Example usage (conceptual):**
```java
Faker faker = new Faker();
String name  = faker.name().fullName();
String email = faker.internet().emailAddress();
String phone = faker.phoneNumber().cellPhone();
```

---

## Test Reports

Test results are compiled into a rich, interactive HTML report using **ExtentReports**.



After execution, a detailed HTML report will be generated at .report location.

**Report Location:**
```
reports/reports.html
```



**The report includes:**
- Overall pass/fail/skip summary with percentage breakdown
- Per-test execution timeline and duration
- Step-by-step logs for each test case
- Screenshots on test failure (if configured)
- Environment metadata (browser, OS, execution mode)

> 📝 The report is overwritten on each test run. To preserve history, back up or rename `reports.html` before re-running.

---

## Logs

Detailed execution logs are generated using **Log4j** and stored in the `logs/` directory.

**Log Location:**
```
logs/
```

**Log levels used:**
| Level   | Purpose                                           |
|---------|---------------------------------------------------|
| `INFO`  | General execution flow (test start, navigation)   |
| `DEBUG` | Detailed step-level information for debugging     |
| `WARN`  | Non-critical issues or unexpected but handled states |
| `ERROR` | Test failures, exceptions, and critical issues    |

**Log configuration** is managed via `src/test/resources/log4j.properties`. You can adjust log verbosity, file rolling policy, and output format there.

---

## Framework Features

| Feature                        | Details                                                   |
|--------------------------------|-----------------------------------------------------------|
| ✅ Page Object Model (POM)      | Clean separation of UI locators and test logic            |
| ✅ Data-Driven Testing          | CSV, JSON, and Excel support via TestNG `@DataProvider`   |
| ✅ Fake Data Generation         | JavaFaker for dynamic, realistic test data at runtime     |
| ✅ Multi-Browser Support        | Chrome, Firefox, Edge — switchable via CLI parameter      |
| ✅ Headless Execution           | Fast, resource-efficient runs for CI/CD pipelines         |
| ✅ Cloud Testing (LambdaTest)   | Real browser cloud with live session monitoring           |
| ✅ Extent HTML Reports          | Rich, interactive test reports with step-level detail     |
| ✅ Log4j Logging                | Structured logs with configurable verbosity levels        |
| ✅ Zero Code Changes for Config | All execution modes driven by Maven CLI parameters        |
| ✅ Maven Surefire Plugin        | Standard, CI-friendly test execution from the command line |

## Integrated the project GitHub Actions
This automation framework is integrated with github actions.
The tests will be executed at 11:30PM IST every single day. 

The reports will be archieved in a gh-pages branch.
You can view the html reports at : 
https://nikhilt1206.github.io/UI-Automation-Framework/report.html
