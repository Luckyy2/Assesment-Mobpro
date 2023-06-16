package org.d3if3001.movies.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3001.movies.model.Barang
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/" +
            "Luckyy2/Json-Barang/main/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

    interface BarangApiService {
        @GET("listbarang.json")
        suspend fun getBarang(): List<Barang>
    }
    object BarangApi {
    val service: BarangApiService by lazy {
        retrofit.create(BarangApiService::class.java)
    }
    fun getBarangUrl(imageId: String): String{
        return "$BASE_URL$imageId.png"
    }
}
enum class ApiStatus { LOADING, SUCCESS, FAILED }
