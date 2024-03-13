package com.greengate.backendtest;

public record TestCase(String name, int statusCode, String postBody, String responseBody) {
}
