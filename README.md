
# 📧 API Automation Framework – Serenity BDD + Rest Assured

This repository contains an **API automation testing framework** built using **Serenity BDD**, **Rest Assured**, **Cucumber**, and **JUnit**, designed for testing REST APIs with **readable BDD-style reports** and scalable project structure.

The framework currently demonstrates API testing using a **SendGrid V3 Mock Server**, but it is **generic and reusable** for any REST API.

---

## 🚀 Tech Stack

* **Java 17**
* **Serenity BDD 4.x**
* **Rest Assured**
* **Cucumber (BDD)**
* **JUnit 4**
* **Gradle**
* **Allure (optional integration)**
* **SLF4J / Logback**

---

## 📂 Project Structure

```
RestAPIAutomationProject
│
├── src
│   ├── test
│   │   ├── java
│   │   │   └── org.automation
│   │   │       ├── apiLogic        # API service classes
│   │   │       ├── runners         # Cucumber runners
│   │   │       └── stepDefinitions # Step definitions
│   │   └── resources
│   │       ├── features            # Cucumber feature files
│   │       ├── apiRequests         # JSON request payloads
│   │       └── serenity.properties # Serenity configuration
│
├── build.gradle
├── settings.gradle
└── README.md
```

---

## 🧪 What This Framework Supports

* ✅ REST API testing using **Rest Assured**
* ✅ BDD-style test cases with **Cucumber**
* ✅ Rich HTML reports using **Serenity BDD**
* ✅ Optional **Allure reporting**
* ✅ Parallel execution support
* ✅ Clean separation of API logic and step definitions
* ✅ Reusable payload management from JSON files

---

## 🌐 Sample API Under Test

**SendGrid V3 Mail Send API (Mock Server)**
Mock endpoint (no authentication required):

```
POST https://sendgrid-v3-api.mock.beeceptor.com/v3/mail/send
```

This allows safe testing of:

* Request payload validation
* Status code assertions
* Logging & reporting
* Framework behavior without real email delivery

---

## 📝 Example Feature File

```gherkin
Feature: Send email using SendGrid V3 Mail Send API

  @positive @smoke
  Scenario: Successfully send a transactional email
    Given the SendGrid mock server base URL is configured
    And a valid email send request payload
    When the client sends a POST request to "/v3/mail/send"
    Then the response status code should be 202
    And the response body should be empty
```

---

## ▶️ How to Run Tests

### 1️⃣ Run all tests

```bash
gradle clean test
```

### 2️⃣ Generate Serenity report

```bash
gradle aggregate
```

### 3️⃣ Open Serenity Report

```
target/site/serenity/index.html
```

---

## 🧾 Serenity Configuration

The framework uses a `serenity.properties` file located at:

```
src/test/resources/serenity.properties
```

Example:

```properties
serenity.project.name=API Automation – Serenity Rest Assured
serenity.test.root=org.automation
serenity.requirements.dir=src/test/resources/features
serenity.outputDirectory=target/site/serenity
serenity.parallel.execution=true
serenity.threads=4
```

---

## 📊 Reports

### Serenity Report

* Automatically generated after test execution
* Location:

  ```
  target/site/serenity/index.html
  ```

### Allure Report (Optional)

If Allure is enabled:

```bash
allure serve target/allure-results
```

---

## 🔧 Prerequisites

* **Java 17+**
* **Gradle 7+**
* **Git**
* (Optional) **Allure CLI**

---

## 🧠 Design Principles

* API logic isolated from step definitions
* No hardcoded payloads (JSON-driven)
* Readable, maintainable BDD scenarios
* Framework-first, API-agnostic design

---

## 📌 Future Enhancements

* 🔐 Authentication (OAuth / API Key)
* 📦 Schema validation
* 🔁 Retry mechanisms
* 📈 CI/CD integration (GitHub Actions / Jenkins)
* 📊 Advanced reporting & dashboards

---

## 👤 Author

**Sushil Yadav**
API Automation | Java | Serenity BDD | Rest Assured

---

## ⭐️ Contributing

Feel free to fork the repository and raise a pull request for improvements, fixes, or enhancements.

---

If you want, I can also:

* Add **badges (build, Java, Serenity)**
* Create a **CI-ready README**
* Simplify for **public open-source**
* Add **example API request JSON**

Just tell me 👍
