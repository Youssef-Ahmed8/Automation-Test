package com.automationexercises.apis;

import com.automationexercises.utils.dataReader.PropertyReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.methods.RequestBuilder;

import java.util.Map;

public class Builder {
    private static String baseURI= PropertyReader.getProperty("baseUrlApi");
    private Builder(){

    }
    //build request specification
    public static RequestSpecification getUserManagementRequestSpecification(Map<String, ?> formParams) {
        return new RequestSpecBuilder().setBaseUri(baseURI)
                .setContentType(ContentType.URLENC)
                .addFormParams(formParams)
                .build();
    }
}
