package pojo.user

import com.fasterxml.jackson.annotation.JsonProperty

data class RegisterRequest(

    @field:JsonProperty("email")
    val email: String,
    @field:JsonProperty("password")
    val password: String,
    @field:JsonProperty("givenName")
    val givenName: String? = null,
    @field:JsonProperty("surname")
    val surname: String? = null

)