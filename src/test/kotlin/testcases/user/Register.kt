package testcases.user

import base.BaseTest
import org.testng.annotations.DataProvider
import org.testng.annotations.Test
import org.testng.asserts.SoftAssert
import pojo.user.RegisterRequest
import util.executeTest
import util.getTestData
import wrappers.user.registerCallWrapper
import wrappers.user.registerRawCallWrapper
import java.util.*

class Register: BaseTest() {

    @Test(dataProvider = "getValidateRegisterTestData")
    fun validateRegister(dataTable: Hashtable<String, String>) {

        // execute or skip the test based on the flag
        executeTest(dataTable["execute"]!!)

        // generate an email as per requirement
        val requestEmail = dataTable["email"]!!.replace("random_number", Random().nextInt(Int.MAX_VALUE).toString())

        // create the request
        val registerRequest = RegisterRequest(
            email = requestEmail,
            password = dataTable["password"]!!,
            givenName = if (dataTable["givenName"] == "null") {
                null
            } else {
                dataTable["givenName"]
            },
            surname = if (dataTable["surname"] == "null") {
                null
            } else {
                dataTable["surname"]
            }
        )

        // make the call and get the response
        val registerResponse = registerCallWrapper(registerRequest)

        // assert the response
        with(SoftAssert()) {

            assertEquals(registerResponse.tokenType, "Bearer", "token_type not matching")
            assertEquals(registerResponse.expiresIn, 10800, "expires_in not matching")
            assertNotNull(registerResponse.accessToken, "access_token is null")
            assertNotNull(registerResponse.refreshToken, "refresh_token is null")
            assertEquals(registerResponse.scope, "offline_access", "scope not matching")

            assertNotNull(registerResponse.groups, "groups is null")
            assertEquals(registerResponse.groups[0], "Users", "groups not matching")

            assertNotNull(registerResponse.userId, "userId is null")

            assertAll()
        }
    }

    @DataProvider
    fun getValidateRegisterTestData(): Array<Array<Any?>> {
        return getTestData(System.getProperty("user.dir") + registerPositiveScenariosCSV)
    }

    @Test(dataProvider = "getValidateRegisterNegativeTestData")
    fun validateRegisterNegative(dataTable: Hashtable<String, String>) {

        // execute or skip the test based on the flag
        executeTest(dataTable["execute"]!!)

        // generate an email as per requirement
        val requestEmail = dataTable["email"]!!.replace("random_number", Random().nextInt(Int.MAX_VALUE).toString())

        // create the request
        val registerRequest = RegisterRequest(
            email = requestEmail,
            password = dataTable["password"]!!,
            givenName = if (dataTable["givenName"] == "null") {
                null
            } else {
                dataTable["givenName"]
            },
            surname = if (dataTable["surname"] == "null") {
                null
            } else {
                dataTable["surname"]
            }
        )

        // make the call and get the response
        val registerResponse = registerRawCallWrapper(registerRequest)
        registerResponse.prettyPrint()

        // assert the response
        with(SoftAssert()) {

            assertEquals(registerResponse.statusCode, dataTable["status"]!!.toInt(), "code not matching")
            assertTrue(registerResponse.body.asString().contains(dataTable["message"]!!), "message [${dataTable["message"]!!}] not found")

            assertAll()
        }
    }

    @DataProvider
    fun getValidateRegisterNegativeTestData(): Array<Array<Any?>> {
        return getTestData(System.getProperty("user.dir") + registerNegativeScenariosCSV)
    }

}