package android.mb00.codehub.api.parser;


public class DateParser {

    public static String parseStandard(String dateAndTime) {
        int endIndex = dateAndTime.indexOf("T");
        String date = dateAndTime.substring(0, endIndex);
        return date;
    }

    public static String parseEnglish(String dateAndTime) {
        String year = dateAndTime.substring(0, 4);
        String month = dateAndTime.substring(5, 7);
        String day = dateAndTime.substring(8, 10);

        if (day.charAt(0) == '0') {
            day = day.replace("0", "");
        }

        switch(month) {
            case "01":
                month = "Jan";
                break;
            case "02":
                month = "Feb";
                break;
            case "03":
                month = "Mar";
                break;
            case "04":
                month = "Apr";
                break;
            case "05":
                month = "May";
                break;
            case "06":
                month = "Jun";
                break;
            case "07":
                month = "Jul";
                break;
            case "08":
                month = "Aug";
                break;
            case "09":
                month = "Sep";
                break;
            case "10":
                month = "Oct";
                break;
            case "11":
                month = "Nov";
                break;
            case "12":
                month = "Dec";
                break;
        }

        String date = month + " " +  day + ", " + year;
        return date;
    }

}
