package connection

import com.moczul.ok2curl.CurlInterceptor
import com.moczul.ok2curl.logger.Loggable
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor


class RestConnection {
    private var client: OkHttpClient
    private val logging = HttpLoggingInterceptor()

    init{
        logging.level = (HttpLoggingInterceptor.Level.HEADERS)
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(logging)

        clientBuilder.addInterceptor(CurlInterceptor { message -> println(message) })
            .build()


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