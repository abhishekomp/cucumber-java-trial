# 📚 Cucumber Java Trial — Learner Guide

> A hands-on guide to understanding every concept demonstrated in this project.

---

## Table of Contents

1. [Project Overview](#1-project-overview)
2. [Tech Stack & Dependencies](#2-tech-stack--dependencies)
3. [Project Structure](#3-project-structure)
4. [Core Concepts](#4-core-concepts)
   - [4.1 What is Cucumber?](#41-what-is-cucumber)
   - [4.2 Feature Files (.feature)](#42-feature-files-feature)
   - [4.3 Step Definitions](#43-step-definitions)
   - [4.4 The Test Runner](#44-the-test-runner)
   - [4.5 Hooks (@Before / @After)](#45-hooks-before--after)
   - [4.6 Scenario Outline & Examples](#46-scenario-outline--examples)
   - [4.7 Data Tables](#47-data-tables)
   - [4.8 Custom Parameter Types (@ParameterType)](#48-custom-parameter-types-parametertype)
   - [4.9 DataTable Type Conversion (@DataTableType)](#49-datatable-type-conversion-datatabletype)
   - [4.10 Soft Assertions with AssertJ](#410-soft-assertions-with-assertj)
   - [4.11 Tags & Filtering](#411-tags--filtering)
5. [Model Classes](#5-model-classes)
6. [Enums & Custom Types](#6-enums--custom-types)
7. [Plain JUnit Tests](#7-plain-junit-tests)
8. [GitHub Actions Workflows](#8-github-actions-workflows)
   - [8.1 Dump Variables](#81-dump-variables)
   - [8.2 Repository Dispatch (API-triggered)](#82-repository-dispatch-api-triggered)
   - [8.3 Workflow Dispatch (Manual)](#83-workflow-dispatch-manual)
   - [8.4 Passing Inputs to Cucumber via Environment Variables](#84-passing-inputs-to-cucumber-via-environment-variables)
9. [Running the Tests Locally](#9-running-the-tests-locally)
10. [Key Takeaways & Common Pitfalls](#10-key-takeaways--common-pitfalls)

---

## 1. Project Overview

This project is a **learning sandbox** for Cucumber BDD (Behaviour-Driven Development) with Java. It covers:

- Writing `.feature` files in Gherkin syntax
- Mapping Gherkin steps to Java step definitions
- Using Scenario Outlines for data-driven testing
- Working with Data Tables (inline tables in Gherkin)
- Defining custom parameter types so Cucumber can convert strings to Java objects
- Lifecycle hooks (`@Before` / `@After`) with ordering
- Soft assertions — collect all failures before reporting
- Triggering tests from GitHub Actions, both manually and via REST API calls

---

## 2. Tech Stack & Dependencies

| Library | Version | Purpose |
|---|---|---|
| **Java** | 21 | Language |
| **Maven** | — | Build tool |
| **Cucumber Java** | 7.8.1 | Core BDD engine |
| **Cucumber JUnit** | 7.8.1 | JUnit 4 runner for Cucumber |
| **Cucumber PicoContainer** | 7.8.1 | Dependency injection between step definition classes |
| **JUnit 4** | 4.13.2 | Test runner framework |
| **AssertJ** | 3.27.3 | Fluent assertions (including SoftAssertions) |

> **Why PicoContainer?**  
> Cucumber creates a new instance of each step definition class for every scenario. If you need to share state between two step definition classes in the same scenario, PicoContainer injects the shared object automatically through constructor injection.

---

## 3. Project Structure

```
src/
└── test/
    ├── java/org/abhishek/
    │   ├── SimpleConcatenation.java         # Tiny production-like utility class
    │   ├── SimpleConcatenationTest.java     # Plain JUnit 4 test (no Cucumber)
    │   ├── model/
    │   │   ├── Book.java                    # Data model used in DataTable scenarios
    │   │   └── Credentials.java             # Data model for login scenarios
    │   ├── runner/
    │   │   └── RunCucumberTestDemo.java     # The JUnit test runner for Cucumber
    │   └── steps/
    │       ├── MyStepDefinitions.java       # Main step defs (login, orders, roles)
    │       ├── MyStepDefsDataTable.java     # Step defs for DataTable scenarios
    │       ├── SoftAssertionsStepDefs.java  # Step defs demonstrating SoftAssertions
    │       ├── CucumberParameterTypes.java  # Custom @ParameterType definitions
    │       ├── DataTableTypeDefinitions.java# @DataTableType: row → Book object
    │       ├── MyHook.java                  # @Before / @After hooks with ordering
    │       ├── MyHook2.java                 # Second hook class (same @After order)
    │       ├── UserReference.java           # Custom value-object type for users
    │       ├── SharedConstants.java         # Pre-built UserReference constants
    │       ├── Priority.java                # Enum: LOW / MEDIUM / HIGH
    │       └── Role.java                    # Enum: ADMIN / USER
    └── resources/org/abhishek/featurefiles/
        ├── first_feature.feature            # Login flows, roles, parameter types
        ├── scenario_outline.feature         # Scenario Outline with Examples table
        ├── dataTableDemo.feature            # DataTable & @DataTableType demo
        └── softAssertions.feature           # Soft assertions demo
```

---

## 4. Core Concepts

### 4.1 What is Cucumber?

Cucumber is a **BDD tool** that lets you write tests in plain English (Gherkin). Each line in a feature file is a "step" that Cucumber looks up in your Java step definition classes and executes.

```
Feature (describes a feature)
  └── Scenario (a test case)
        ├── Given  (setup / precondition)
        ├── When   (action)
        └── Then   (assertion / expected outcome)
```

---

### 4.2 Feature Files (.feature)

**File:** `first_feature.feature`

Feature files use the **Gherkin** language. Key syntax elements:

```gherkin
@first-feature                          # ← Tag on the Feature (applies to all scenarios)
Feature: Cucumber First Feature File

  @web-login                            # ← Tag on a specific Scenario
  Scenario: Perform login to web portal
    Given I open the login page for my web portal
    Then I enter the username as "admin" and password as "secret"
    Then I click login button
    Then I am on the homepage
```

- **Tags** (`@web-login`) are labels you apply to features or scenarios. They let you run only a subset of tests (e.g., `--define cucumber.filter.tags=@web-login`).
- `Given` / `When` / `Then` / `And` / `But` are all functionally identical in Cucumber — they are used for readability only.
- Quoted strings like `"admin"` become `String` parameters in the step definition.

---

### 4.3 Step Definitions

**File:** `MyStepDefinitions.java`

Step definitions are the Java methods that Cucumber calls when it matches a Gherkin step.

```java
// The annotation's value is the Gherkin text pattern to match.
// {string} is a built-in Cucumber Expression that captures a quoted string.
@Then("I enter the username as {string} and password as {string}")
public void i_enter_the_username_as_and_password_as(String username, String password) {
    System.out.println("username: " + username + ", password: " + password);
}
```

**Two matching styles are used in this project:**

| Style | Example | Notes |
|---|---|---|
| **Cucumber Expressions** | `{string}`, `{int}`, `{word}` | Preferred, cleaner syntax |
| **Regular Expressions** | `^I open the login page$` | More powerful but verbose |

> You'll see both in `MyStepDefinitions.java`. For new code, prefer Cucumber Expressions.

---

### 4.4 The Test Runner

**File:** `RunCucumberTestDemo.java`

```java
@RunWith(Cucumber.class)           // Use Cucumber as the JUnit runner
@CucumberOptions(
    features = "src/test/resources/",  // Where to find .feature files
    glue = {"org.abhishek.steps"},     // Package(s) where step definitions live
    plugin = {
        "pretty",                      // Prints readable output to console
        "json:target/test-cucumber_report.json",  // JSON report file
        "html:target/html-reports.htm"            // HTML report file
    }
)
public class RunCucumberTestDemo { }
```

This is an empty class — the annotations do all the work. When Maven's Surefire plugin runs tests it finds this class (because it's included in `pom.xml`'s `<includes>` list) and JUnit hands control to Cucumber.

---

### 4.5 Hooks (@Before / @After)

**Files:** `MyHook.java`, `MyHook2.java`

Hooks run code before or after each scenario (or tagged scenarios).

```java
@Before
public void initializeTest() {
    // Runs before EVERY scenario — good for setting up drivers, databases, etc.
}

@After
public void teardown() {
    // Runs after EVERY scenario — good for cleanup
}

// Tag filter: only runs for scenarios tagged @RemoveFiles
// order: lower numbers run LAST for @After (highest = first to run)
@After(value = "@RemoveFiles", order = Integer.MAX_VALUE)
public void performSomeClean3() { ... }
```

**Observed @After execution order** (highest `order` value runs first):

```
order = Integer.MAX_VALUE  ← runs first
order = (default)
order = 2
order = 1
order = 0                  ← runs last
```

> **Multiple hook classes:** `MyHook2.java` also registers an `@After` with `order = Integer.MAX_VALUE` on `@RemoveFiles`. Both fire — Cucumber collects hooks from all glue classes. The order between two hooks of the same `order` value is not guaranteed.

---

### 4.6 Scenario Outline & Examples

**File:** `scenario_outline.feature`

A Scenario Outline lets you run the **same scenario with different data** by defining an `Examples` table.

```gherkin
@scenario-outline-demo-string
Scenario Outline: Login to web portal using scenario outline
  Given I open the login page for my web portal
  Then I enter "<username>" and "<password>"
  Then I click login button
  Then I am on the homepage
  Examples:
    | username | password |
    | admin    | admin    |   ← runs once with admin/admin
    | testing  | qa       |   ← runs again with testing/qa
```

Each row in `Examples` becomes one test scenario. The `<username>` placeholder is replaced with the value from the table column with the matching header.

---

### 4.7 Data Tables

**File:** `dataTableDemo.feature`, `MyStepDefsDataTable.java`

Data Tables are inline tables attached directly to a step. They can be consumed as:

#### As a `DataTable` (raw)
```gherkin
Given the following user credentials:
  | username | password |
  | user1    | pass1    |
  | user2    | pass2    |
```
```java
@Given("the following user credentials:")
public void theFollowingUserCredentials(DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
    // Each map: { "username" -> "user1", "password" -> "pass1" }
}
```

#### As a typed `List<T>` (using `@DataTableType`)
```gherkin
Given the following book details:
  | title            | author              | year |
  | The Great Gatsby | F. Scott Fitzgerald | 1925 |
```
```java
// In DataTableTypeDefinitions.java — teaches Cucumber how to convert a row to a Book
@DataTableType
public Book defineBook(Map<String, String> row) {
    return new Book(row.get("title"), row.get("author"),
                    Integer.parseInt(row.get("year")), row.get("genre"));
}

// In the step definition — Cucumber auto-converts each row to a Book
@Given("the following book details:")
public void theFollowingBookDetails(List<Book> books) { ... }
```

---

### 4.8 Custom Parameter Types (@ParameterType)

**File:** `CucumberParameterTypes.java`

`@ParameterType` lets you define your own `{customType}` placeholder that Cucumber can use in step expressions. You give it a regex to match and a Java method to convert the matched string.

```java
// Matches strings like LOW, MEDIUM, HIGH and converts to the Priority enum
@ParameterType("LOW|MEDIUM|HIGH")
public Priority priority(String value) {
    return Priority.valueOf(value);
}
```

Usage in feature file:
```gherkin
Given I have an Order with Order number T-12345 with priority HIGH and delivery date 2025-12-31
```

Matching step definition:
```java
@Given("I have an Order with Order number {orderNum} with priority {priority} and delivery date {isoDate}")
public void iHaveAnOrder(String orderNum, Priority priority, LocalDate isoDate) { ... }
```

All three `{orderNum}`, `{priority}`, and `{isoDate}` are custom types — Cucumber automatically calls the matching `@ParameterType` method for each one.

**Custom types defined in this project:**

| Placeholder | Regex | Java Type |
|---|---|---|
| `{userReference}` | `[A-Z_0-9]+` | `UserReference` |
| `{priority}` | `LOW\|MEDIUM\|HIGH` | `Priority` enum |
| `{isoDate}` | `\d{4}-\d{2}-\d{2}` | `LocalDate` |
| `{orderNum}` | `T-\d{5}` | `String` (validates format) |

> **Note:** The `{orderNum}` regex requires **exactly 5 digits** after `T-`. In `first_feature.feature` there is a scenario with `T-123456` (6 digits) that will **fail to match** — this is intentional, to demonstrate what happens when the pattern doesn't match.

---

### 4.9 DataTable Type Conversion (@DataTableType)

**File:** `DataTableTypeDefinitions.java`

While `@ParameterType` handles single inline values, `@DataTableType` handles **table rows**. It maps a `Map<String, String>` (one row) to a custom Java object.

```java
@DataTableType
public Book defineBook(Map<String, String> row) {
    return new Book(
        row.get("title"),
        row.get("author"),
        Integer.parseInt(row.get("year")),
        row.get("genre")
    );
}
```

Once registered, any step that accepts `List<Book>` will automatically use this converter.

---

### 4.10 Soft Assertions with AssertJ

**File:** `SoftAssertionsStepDefs.java`, `softAssertions.feature`

Normally, the first failing assertion stops the test immediately. **Soft assertions** collect all failures and report them together at the end.

```java
SoftAssertions softly = new SoftAssertions();

for (int i = 0; i < userDataTable.size(); i++) {
    String expected = userDataTable.get(i).get("expectedError");
    String actual   = actualErrors.get(i);

    softly.assertThat(actual)
          .as("Row %d: expected '%s' but got '%s'", i, expected, actual)
          .isEqualTo(expected);  // Failure is recorded, NOT thrown yet
}

softly.assertAll();  // ← This is where all collected failures are thrown at once
```

This is valuable when you want to validate many things in a single scenario and see **all the problems** in one run.

---

### 4.11 Tags & Filtering

Tags are used both in feature files and in workflow commands:

```yaml
# Run only scenarios tagged @web-login
run: mvn test -Dtest=RunCucumberTestDemo --define cucumber.filter.tags=@web-login

# Run only scenarios tagged @process_order
run: mvn test -Dtest=RunCucumberTestDemo --define cucumber.filter.tags=@process_order
```

Tags used in this project:

| Tag | Where | Purpose |
|---|---|---|
| `@first-feature` | Feature | Groups all scenarios in first_feature.feature |
| `@web-login` | Scenario | Login-related scenarios |
| `@web-login-simple` | Scenario | Simple string-param login scenario |
| `@web-login-param` | Scenario | Login using data table params |
| `@web-navigation` | Scenario | Navigation-related scenarios |
| `@process_order` | Scenario | Order processing workflow |
| `@RemoveFiles` | Scenario | Scenarios that trigger file-cleanup hooks |
| `@scenario_outline` | Feature | All scenario outline scenarios |
| `@scenario-outline-demo-string` | Scenario | String-based outline |
| `@scenario-outline-index` | Scenario | Integer index-based outline |

---

## 5. Model Classes

### `Book.java`
A simple POJO with four fields: `title`, `author`, `year`, `genre`. Used to demonstrate how Cucumber can convert DataTable rows into typed objects via `@DataTableType`.

### `Credentials.java`
A simple POJO with `username` and `password`. Used in the DataTable credentials demo.

Both classes follow standard Java conventions: constructor, getters/setters, `toString()`.

---

## 6. Enums & Custom Types

### `Priority.java`
```java
public enum Priority { LOW, MEDIUM, HIGH }
```
Used as a custom Cucumber parameter type. The `@ParameterType` in `CucumberParameterTypes.java` bridges the Gherkin string `"HIGH"` to `Priority.HIGH`.

### `Role.java`
```java
public enum Role { ADMIN, USER }
```
Used directly in step definitions with `{}` (anonymous parameter type) — Cucumber will try to match enum values by name automatically.

### `UserReference.java`
A **value object** (immutable, identity based on its string `reference`). It has proper `equals()` and `hashCode()` implementations. The `@ParameterType` maps any uppercase/underscore string from Gherkin to a `UserReference` instance.

### `SharedConstants.java`
Pre-built `UserReference` constants for convenience:
```java
public static final UserReference SUPER_USER = UserReference.valueOf("SUPER_USER");
```

---

## 7. Plain JUnit Tests

### `SimpleConcatenation.java` + `SimpleConcatenationTest.java`

These files show a basic JUnit 4 test without any Cucumber involvement — a useful reference point for comparison.

```java
// Two assertion styles side by side:
assertEquals("HelloWorld", result);          // Classic JUnit 4 assertion
assertThat(result).isEqualTo("HelloWorld");  // AssertJ fluent assertion (preferred)
```

AssertJ is generally preferred because:
- Error messages are much clearer
- The API is fluent and discoverable
- It supports advanced assertions (collections, strings, exceptions, etc.)

---

## 8. GitHub Actions Workflows

All workflows live in `.github/workflows/`. There are two types of trigger used:

| Trigger | When it fires |
|---|---|
| `workflow_dispatch` | Manually, from the GitHub Actions UI (or via REST API) |
| `repository_dispatch` | Via an HTTP POST to GitHub's API (e.g., from Postman or another service) |

---

### 8.1 Dump Variables

**File:** `dumpVariable.yml`

A diagnostic workflow. It builds the project and then **prints out all GitHub Actions context objects** (github, job, steps, runner, strategy, matrix) as JSON. Very useful for understanding what data is available inside a workflow run.

---

### 8.2 Repository Dispatch (API-triggered)

**Files:** `repo_dispatch.yml`, `repo_dispatch_maven.yml`, `repo_dispatch_maven_scenario_outline.yml`

These workflows are triggered by an HTTP POST to GitHub's API. Example `curl` call:
```bash
curl -X POST \
  -H "Authorization: token YOUR_PAT" \
  -H "Accept: application/vnd.github.v3+json" \
  https://api.github.com/repos/OWNER/REPO/dispatches \
  -d '{"event_type":"run-ping","client_payload":{"example-key":"hello"}}'
```

The `event_type` must match the `types:` value in the workflow. The `client_payload` is arbitrary JSON you provide — accessed in the workflow as `${{ github.event.client_payload.your-key }}`.

---

### 8.3 Workflow Dispatch (Manual)

**Files:** `runCucumberWorkflowDispatch.yml`, `worflowdispatch_with_inputs.yml`, `worflowdispatch_process_incoming_order.yml`

`workflow_dispatch` triggers let you run the workflow from the **GitHub Actions UI** or via the API. You can define `inputs` that appear as form fields in the UI:

```yaml
on:
  workflow_dispatch:
    inputs:
      ordernumber:
        description: 'Enter Order number to process'
        required: true
        default: ''
```

The input value is accessed as `${{ github.event.inputs.ordernumber }}`.

---

### 8.4 Passing Inputs to Cucumber via Environment Variables

The key integration pattern in this project is passing a runtime value (e.g., an order number) from the workflow into a Cucumber test:

**Workflow side:**
```yaml
- name: Run Cucumber Tests
  env:
    TEST_ORDERNUM: ${{ github.event.inputs.ordernumber }}  # Set as env var
  run: mvn test -Dtest=RunCucumberTestDemo --define cucumber.filter.tags=@process_order
```

**Java step definition side:**
```java
@Given("An Order number is received")
public void anOrderNumberIsReceived() {
    System.out.println("Processing OrderNumber = " + System.getenv("TEST_ORDERNUM"));
}
```

This is a simple but powerful pattern for parameterising your test runs from CI/CD.

---

## 9. Running the Tests Locally

**Run all tests:**
```bash
mvn test
```

**Run only Cucumber tests:**
```bash
mvn test -Dtest=RunCucumberTestDemo
```

**Run only scenarios with a specific tag:**
```bash
mvn test -Dtest=RunCucumberTestDemo --define cucumber.filter.tags=@web-login
```

**Run with an environment variable (simulating workflow input):**
```bash
TEST_ORDERNUM=T-12345 mvn test -Dtest=RunCucumberTestDemo --define cucumber.filter.tags=@process_order
```

After running, find the reports in:
- `target/test-cucumber_report.json` — machine-readable JSON report
- `target/html-reports.htm` — human-readable HTML report
- `target/surefire-reports/` — Surefire XML reports

---

## 10. Key Takeaways & Common Pitfalls

| # | Concept | Pitfall to Avoid |
|---|---|---|
| 1 | Step definition text must match Gherkin **exactly** | A missing space or different capitalisation = `Undefined step` error |
| 2 | `@ParameterType` method name **must** match the placeholder name in `{}` | `{priority}` → method must be named `priority()` |
| 3 | `@DataTableType` is separate from `@ParameterType` | Use `@DataTableType` for table rows, `@ParameterType` for inline values |
| 4 | `@Before`/`@After` run per scenario, not per feature | Heavy setup should be tagged and order-controlled |
| 5 | `softly.assertAll()` must be called | Without it, soft assertion failures are silently swallowed |
| 6 | `mvn -B package` **runs tests** (package phase includes test phase) | Don't call it "Install Maven" — Maven is pre-installed on GitHub runners |
| 7 | `client_payload` is only set for `repository_dispatch` | Don't read it in a `workflow_dispatch` workflow — it will be empty |
| 8 | `{orderNum}` regex is `T-\d{5}` (exactly 5 digits) | `T-123456` (6 digits) will not match and the scenario will show as undefined |
| 9 | The test runner class is `RunCucumberTestDemo` | Some workflows reference `TestRunner` or `CucumberTest` — those don't exist |

