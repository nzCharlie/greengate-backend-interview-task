# Greengate "Multi-Currency Invoice" Code Test


afdafdafdasf

Your objective is to create a Restful multi-currency invoicing service which uses frankfurter.app as the exchange rate input.

## Requirements

**API availability**

Please let us know the command to start up the server listening for request for API on `http://localhost:8080` Ideally swagger-ui should also be available (let us know how to access the swagger-ui). Both `POST http://localhost:8080/invoice/total` and `GET http://localhost:8080/exchange-rate/{date}` endpoints should be accessible.

We will be using the supplied RestAssured tests found in `src/test/java/com/greengate/backendtest/MultiCurrencyInvoiceTest.java` to evaluate your submission.

**Provided material**

An OpenAPI specification has been provided to you in `openapi/openapi.yaml`. You are expected to implement the APIs according to the specification. It is advisable to make sure you understand your task by loading the specification in https://editor.swagger.io/.

_Please note_: Feel free to utilise https://editor.swagger.io/ to generate code stubs, however only use it if you feel it will save time using it. We are more interested in your ability to implement the API than your ability to use the editor.

**Additional Notes On /invoice/total endpoint**

- The invoice date will be in the RFC3339 Internet date format e.g. `2020-07-27`
- The invoice base currency will be in ISO4217 alphabetic code format e.g. `NZD`
- Each invoice line should include a description, an decimal amount (to 2 places), and a currency e.g. `AUD`

Example request (per the supplied `openapi/openapi.yaml` file):

```
{"invoice":{"currency":"NZD","date":"2020-07-07","lines":[{"description":"Intel Core i9","currency":"USD","amount":700},{"description":"ASUS ROG Strix","currency":"AUD","amount":500}]}}
```

**Actions**

Once `/invoice/total` API has successful parse the request body, it should:

- Use the frankfurter.app API to retrieve the exchange rate for each of the currency pairs.
- Calculate each line amount in the base currency, and call it the line total.
- Calculate the invoice total in the base currency and return the result in `plain/text`.

**Expected Output**

- On Success (HTTP status code `200`): Invoice total in the base currency e.g. `1600.86`
- On Error:
  - Failed to fetch relevant exchange rate data (HTTP status code `404`): An appropriate error message prefixed with `Error: `
  - Failed to parse request body, including invalid/missing required input (HTTP status code `400`): An appropriate error message prefixed with `Error: `
  - Failed to calculate total (HTTP status code `500`): An appropriate error message prefixed with `Error:`

**Assumptions**

- Exchange rates should be calculated at a precision of 4 decimal places - you must round the Exchange Rates API rates before using them to convert line amounts to line totals.
- Line totals and the invoice total only need to be calculated to 2 decimal places - you must round each line total.
- When rounding these numbers, you should round according to standard rules.
  - Rounding should match the [Google Sheets ROUND function](https://support.google.com/docs/answer/3093440?hl=en), for example.

**Example API Call**

A request/response to/from the api.frankfurter.app API might look like the following:

`GET https://api.frankfurter.app/2020-07-07?from=NZD&to=USD,AUD`

```
{"amount":1.0,"base":"NZD","date":"2020-07-07","rates":{"AUD":0.94212,"USD":0.65411}}
```

When converting your line amounts, please pay attention to the direction of the exchange rate you've retrieved from api.frankfurter.app e.g. USD -> NZD vs NZD -> USD. Each line amount should be converted from the line currency to the invoice currency.

**Programming Language and Framework**

Your solution must be implemented in Java. You should be using one of a framework such as [Quarkus](https://quarkus.io/), [Spring Boot](https://spring.io/projects/spring-boot), [Micronaut](https://micronaut.io/) or [Dropwizard](https://www.dropwizard.io/en/stable/). FYI, Greengate uses Quarkus.

## Provided Material

**Files you must change**

We've provided the following files which we expect you will change:

- `pom.xml` should be updated to add any dependencies you require. It is used by maven, which is the build tool for this test.
- `src/main` directory can be used to add your implementation. Add any files as you need to complete the test.

**Files you must NOT change**

- Any files in `src/test/java/com/greengate/backendtest` is used by us to run evaluation against your submission. Any modification made there will be overriden by us as part of evaluation.
- Any other files not explicitly mentioned by **Files you must change**.

## Checking Your Solution

Before you submit your solution to us, we'd like you to check that the following command runs successfully by following command.

| OS           | Command       |
| ------------ | ------------- |
| OSX or Linux | `./mvnw test` |
| Windows      | `mvnw test`   |

## Submission Format

Please ZIP your project folder, including a `.git` directory with at least one commit, and provide this to our recruiter.

## Evaluation Criteria

1. We will check that you have correctly followed the instructions per this document.

2. After reviewing your code, we will attempt to the provided RestAssured tests.

3. We will run your code against a more extensive version of RestAssured tests we have created, with additional test cases to evaluate additional edge cases.

4. We will score your submission against:

   - Code Design
   - Quality
   - Error Handling
   - Code Performance
   - Security
   - Operability
   - Documentation

## Time Limits

We expect a solution can be completed within 1-3 hours total development time.

Please do not attempt to add additional features we have not asked for. We want a working solution which addresses our evaluation criteria - nothing more.

If you do not believe you can complete a solution in under 3 hours, please let us know. We do not "police" this time limit - it simply exists to be respectful of your time.
