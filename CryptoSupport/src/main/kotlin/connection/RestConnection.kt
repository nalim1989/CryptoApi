package connection

import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor


class RestConnection {
    private var client: OkHttpClient
    private val logging = HttpLoggingInterceptor()

    init{
        logging.level = (HttpLoggingInterceptor.Level.HEADERS)
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(logging)
        client=clientBuilder.build()
    }

    fun post(url:String, formBody:RequestBody, headers: Headers): String {
        val request: Request = Request.Builder()
            .url(url)
            .headers(headers)
            .post(formBody)
            .build()
        client.newCall(request).execute().use { response -> return response.body!!.string() }
    }


    fun get(url:String): String {
        val request: Request = Request.Builder()
            .url(url)
            .get()
            .build()
        client.newCall(request).execute().use { response -> return response.body!!.string() }
    }
}