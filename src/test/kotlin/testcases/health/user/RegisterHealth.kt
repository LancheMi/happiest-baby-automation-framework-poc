package testcases.health.user

import base.BaseTest
import org.testng.annotations.Test
import pojo.user.RegisterRequest
import util.executeTest
import wrappers.user.registerCallWrapper
import java.util.*

class RegisterHealth: BaseTest() {

    @Test
    fun registerHealthTest() {
        // execute or skip the test based on the flag
        executeTest(true)

        // create the request
        val registerRequest = RegisterRequest(
            email = "automation_interview+${Random().nextInt(Int.MAX_VALUE)}@mail.com",
            password = "Jordan123",
            givenName = "Michael",
            surname = "Jordan"
        )

        // make the successful call
        registerCallWrapper(registerRequest)
    }
}