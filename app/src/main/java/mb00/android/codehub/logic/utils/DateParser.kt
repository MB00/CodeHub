package mb00.android.codehub.logic.utils

/**
 * Contains methods used to parse dates from the GitHub API
 */

object DateParser {

    fun parseStandard(dateAndTime: String?): String? {
        val endIndex = dateAndTime?.indexOf("T") ?: 0
        return dateAndTime?.substring(0, endIndex)
    }

    fun parseEnglish(dateAndTime: String?): String? {
        val year = dateAndTime?.substring(0, 4)
        var month = dateAndTime?.substring(5, 7)
        var day = dateAndTime?.substring(8, 10)

        if (day?.get(0) == '0') {
            day = day.replace("0", "")
        }

        when (month) {
            "01" -> month = "Jan"
            "02" -> month = "Feb"
            "03" -> month = "Mar"
            "04" -> month = "Apr"
            "05" -> month = "May"
            "06" -> month = "Jun"
            "07" -> month = "Jul"
            "08" -> month = "Aug"
            "09" -> month = "Sep"
            "10" -> month = "Oct"
            "11" -> month = "Nov"
            "12" -> month = "Dec"
        }

        return "$month $day, $year"
    }

}
