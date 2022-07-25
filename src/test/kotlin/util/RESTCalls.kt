package util

import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.response.Response

fun postRequest(uriString: String, requestBody: Any): Response {
    println("Inside POST request call")

    return Given {
        log().all()
        contentType(ContentType.JSON)
        body(requestBody)
    } When {
        post(uriString)
    } Then {

    } Extract {
        response()
    }
}