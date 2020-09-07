package dev.ahmdaeyz.gadsleaderboard.data.network.service.apis

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

private const val BASE_URL = "https://docs.google.com/forms/d/e/"

object GoogleFormsAPIBuilder {
    operator fun invoke(): GoogleFormsAPI {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)

        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build().create(GoogleFormsAPI::class.java)
    }
}
