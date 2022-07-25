package builders.user

import pojo.user.RegisterRequest
import java.util.*

fun createRegisterRequest(dataTable: Hashtable<String, String>): RegisterRequest {
    return RegisterRequest(
        email = dataTable["email"]!!,
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
}