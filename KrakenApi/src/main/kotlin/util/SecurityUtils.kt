package util

import java.security.MessageDigest
import java.util.*
import javax.swing.Action.DEFAULT

class SecurityUtils {

    companion object {
        fun calculateSha256(data: String): ByteArray {
            return MessageDigest
                .getInstance("SHA-256")
                .digest(data.toByteArray())

        }

        fun bytesToHex(hash: ByteArray): String {
            val hexString = StringBuilder(2 * hash.size)
            for (i in hash.indices) {
                val hex = Integer.toHexString(0xff and hash[i].toInt())
                if (hex.length == 1) {
                    hexString.append('0')
                }
                hexString.append(hex)
            }
            return hexString.toString()

        }

    }

}