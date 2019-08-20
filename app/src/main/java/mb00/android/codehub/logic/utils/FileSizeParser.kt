package mb00.android.codehub.logic.utils

import java.math.BigDecimal
import java.math.MathContext

/**
 * Contains methods used to parse file sizes from the GitHub API
 */

object FileSizeParser {

    fun parseSize(size: String?): String? {
        size?.let {
            return when {
                size.length <= 3 -> returnBytes(size)
                size.length == 4 || size.length == 5 -> returnKilobytes(size)
                size.length == 6 -> returnMegabytes(size)
                else -> size // some horrific edge case
            }
        }
    }

    private fun returnBytes(size: String?): String? {
        return "$size B"
    }

    private fun returnKilobytes(size: String?): String? {
        val kilobytesInteger = size?.substring(0, size.length - 3)
        val kilobytesFractional = size?.substring(size.length - 3)
        val sizeInKilobytes = "$kilobytesInteger.$kilobytesFractional"
        return BigDecimal(sizeInKilobytes).round(MathContext(2)).toString() + " KB"
    }

    private fun returnMegabytes(size: String?): String? {
        val megabytesInteger = size?.substring(0, 1)
        val megabytesFractional = size?.substring(1)
        val sizeInMegabytes = "$megabytesInteger.$megabytesFractional"
        return BigDecimal(sizeInMegabytes).round(MathContext(2)).toString() + " MB"
    }

}
