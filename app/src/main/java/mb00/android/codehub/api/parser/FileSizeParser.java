package mb00.android.codehub.api.parser;

import java.math.BigDecimal;
import java.math.MathContext;


public class FileSizeParser {

    public static String parseSize(String size) {
        if (size.length() <= 3) { // size in bytes
            size += "B";
            return size;
        } else if (size.length() == 4) { // size in kilobytes
            String kilobytesInteger = size.substring(0, 1);
            String kilobytesFractional = size.substring(1);
            String sizeInKilobytes = kilobytesInteger + "." + kilobytesFractional;
            size = new BigDecimal(sizeInKilobytes).round(new MathContext(2)).toString() + "KB";
            return size;
        } else if (size.length() == 5) { // size in kilobytes
            String kilobytesInteger = size.substring(0, 2);
            String kilobytesFractional = size.substring(2);
            String sizeInKilobytes = kilobytesInteger + "." + kilobytesFractional;
            size = new BigDecimal(sizeInKilobytes).round(new MathContext(2)).toString() + "KB";
            return size;
        } else if (size.length() == 6){ // size in megabytes;
            String megabytesInteger = size.substring(0, 1);
            String megabytesFractional = size.substring(1);
            String sizeInMegabytes = megabytesInteger + "." + megabytesFractional;
            size = new BigDecimal(sizeInMegabytes).round(new MathContext(2)).toString() + "MB";
            return size;
        } else { // some horrific edge case
            return size;
        }
    }

}
