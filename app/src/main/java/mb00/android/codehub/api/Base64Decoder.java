package mb00.android.codehub.api;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Static method used to decode content from the GitHub API
 */

public class Base64Decoder {

    public static String decodeBase64(String coded){
        byte[] valueDecoded= new byte[0];
        try {
            valueDecoded = Base64.decode(coded.getBytes("UTF-8"), Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
            // empty for now
        }
        return new String(valueDecoded);
    }

}
