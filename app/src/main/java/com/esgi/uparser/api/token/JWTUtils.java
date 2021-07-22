package com.esgi.uparser.api.token;

import android.util.Base64;
import java.io.UnsupportedEncodingException;

public class JWTUtils {

    public static TokenResponse decoded(String JWTEncoded) throws Exception {
        String[] split;
        TokenResponse tokenResponse = new TokenResponse("", "");
        try {
            split = JWTEncoded.split("\\.");
            String jsonUserResponse = getJson(split[1]);
            tokenResponse.setEmail(jsonUserResponse.replace("\"", "'").split("'email':'")[1].split("','userId'")[0]);
            tokenResponse.setUserId(jsonUserResponse.replace("\"", "'").split("','userId':'")[1].split("','roles'")[0]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return tokenResponse;
    }

    private static String getJson(String strEncoded) throws UnsupportedEncodingException {
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        return new String(decodedBytes, "UTF-8");
    }
}
