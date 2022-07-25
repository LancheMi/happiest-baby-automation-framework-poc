package wrappers.user

import base.BaseTest.Companion.happiestBabyBaseUri
import base.BaseTest.Companion.userRegisterEndPoint
import builders.user.createRegisterRequest
import io.restassured.response.Response
import org.testng.Assert
import pojo.user.RegisterRequest
import pojo.user.RegisterResponse
import util.OK
import util.postRequest
import java.util.*

fun registerCallWrapper(registerRequest: RegisterRequest): RegisterResponse {
    return validateAndDeserialize(registerRequest)
}

fun registerCallWrapper(dataTable: Hashtable<String, String>): RegisterResponse {
    return validateAndDeserialize(createRegisterRequest(dataTable))
}

fun registerRawCallWrapper(registerRequest: RegisterRequest): Response {
    return postRequest(happiestBabyBaseUri + userRegisterEndPoint, registerRequest)
}

fun registerRawCallWrapper(dataTable: Hashtable<String, String>): Response {
    return postRequest(happiestBabyBaseUri + userRegisterEndPoint, createRegisterRequest(dataTable))
}

private fun validateAndDeserialize(registerRequest: RegisterRequest): RegisterResponse {
    val registerResponse: Response = postRequest(happiestBabyBaseUri + userRegisterEndPoint, registerRequest)
    registerResponse.prettyPrint()
    Assert.assertEquals(registerResponse.statusCode, OK, "register status code is not 200")
    return registerResponse.`as`(RegisterResponse::class.java)
}