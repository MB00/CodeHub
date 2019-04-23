package mb00.android.codehub.logic.utils

import android.util.Base64

import java.io.UnsupportedEncodingException

/**
 * Static method used to decode content from the GitHub API
 */

object Base64Decoder {

    fun decodeBase64(coded: String): String {
        var valueDecoded = ByteArray(0)
        try {
            valueDecoded = Base64.decode(coded.toByteArray(charset("UTF-8")), Base64.DEFAULT)
        } catch (e: UnsupportedEncodingException) {
            // empty for now
        }
        return String(valueDecoded)
    }

}
