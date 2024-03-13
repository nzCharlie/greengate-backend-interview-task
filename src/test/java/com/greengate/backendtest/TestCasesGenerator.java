package com.greengate.backendtest;

import java.util.List;

public interface TestCasesGenerator {

    default List<TestCase> getTestCases() {
        return List.of(
                new TestCase("Test Case 1", 200, """
                        {
                          "invoice": {
                            "currency": "NZD",
                            "date": "2020-07-07",
                            "lines": [
                              {
                                "description": "Intel Core i9",
                                "currency": "USD",
                                "amount": 700
                              },
                              {
                                "description": "ASUS ROG Strix",
                                "currency": "AUD",
                                "amount": 500
                              }
                            ]
                          }
                        }
                        """, "1601.86")
        );
    }
}
