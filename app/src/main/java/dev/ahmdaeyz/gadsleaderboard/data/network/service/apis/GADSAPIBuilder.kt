package dev.ahmdaeyz.gadsleaderboard.data.network.service.apis

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit


private const val BASE_URL = "https://gadsapi.herokuapp.com/api/"

object GADSAPIBuilder {

    operator fun invoke(): GADSAPI {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)

        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val contentType = "application/json".toMediaType()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .client(okHttpClient)
            .build().create(GADSAPI::class.java)
    }
}