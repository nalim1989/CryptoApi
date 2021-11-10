package util

import okhttp3.FormBody
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody


class UriRequestBuilder {

    companion object{
        fun encodeAndBuild(params:Map<String,String>):RequestBody{
            val builder = FormBody.Builder()
            for (entry in params.entries) {
                builder.add(entry.key, entry.value)
            }
            return builder.build()
        }

        fun build(params:Map<String,String>, mediaType: MediaType):RequestBody{
            val bodyString:String = params.flatMap { (key, value) -> listOf("$key=$value") }.joinToString("&")
            return bodyString.toRequestBody(mediaType);
        }

    }
}