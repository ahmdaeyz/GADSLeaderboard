package dev.ahmdaeyz.gadsleaderboard.data.network.service.apis

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface GoogleFormsAPI {
    @POST("1e_qJ5U6uEQhiKKE0GtyY9apF0arVxlnkUdJ639KyP5Q/formResponse")
    @FormUrlEncoded
    suspend fun submitProjectToForm(
        @Field("entry.372036559") firstName: String,
        @Field("entry.287574127") lastName: String,
        @Field("entry.1655329411") email: String,
        @Field("entry.18962072") projectGithubUrl: String
    ): Response<Unit>
}