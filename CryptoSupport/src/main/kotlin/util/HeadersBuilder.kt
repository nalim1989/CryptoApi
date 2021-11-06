package util

import okhttp3.FormBody
import okhttp3.Headers
import okhttp3.RequestBody

class HeadersBuilder {

    companion object {
        fun build(headers: Map<String, String>): Headers {
            val builder = Headers.Builder()

            for (entry in headers.entries) {
                builder.add(entry.key, entry.value)
            }

            return builder.build()
        }
    }
}