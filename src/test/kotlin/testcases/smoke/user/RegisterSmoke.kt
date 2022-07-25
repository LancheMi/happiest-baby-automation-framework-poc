package testcases.smoke.user

import base.BaseTest
import org.testng.Assert.assertNotNull
import org.testng.annotations.Test
import pojo.user.RegisterRequest
import util.executeTest
import wrappers.user.registerCallWrapper
import java.util.*

class RegisterSmoke: BaseTest() {

    @Test
    fun registerSmokeTest() {
        // execute or skip the test based on the flag
        executeTest(true)

        // create the request
        val registerRequest = RegisterRequest(
            email = "automation_interview+${Random().nextInt(Int.MAX_VALUE)}@mail.com",
            password = "Jordan123",
            givenName = "Michael",
            surname = "Jordan"
        )

        // make the call and get the response
        val registerResponse = registerCallWrapper(registerRequest)

        // assert one attribute from the response
        assertNotNull(registerResponse.accessToken, "access_token is null")
    }
}