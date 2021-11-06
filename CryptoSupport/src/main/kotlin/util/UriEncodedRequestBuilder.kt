package util

import okhttp3.FormBody
import okhttp3.RequestBody


class UriEncodedRequestBuilder {

    companion object{
        fun build(params:Map<String,String>):RequestBody{
            val builder = FormBody.Builder()
            for (entry in params.entries) {
                builder.add(entry.key, entry.value)
            }
            return builder.build()
        }

    }
}