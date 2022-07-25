package pojo.user

import com.fasterxml.jackson.annotation.JsonProperty

data class RegisterResponse(

    @field:JsonProperty("token_type")
    val tokenType: String,

    @field:JsonProperty("expires_in")
    val expiresIn: Int,

    @field:JsonProperty("access_token")
    val accessToken: String,

    @field:JsonProperty("refresh_token")
    val refreshToken: String,

    @field:JsonProperty("scope")
    val scope: String,

    @field:JsonProperty("groups")
    val groups: List<String>,

    @field:JsonProperty("userId")
    val userId: String

)
