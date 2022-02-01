package com.etgpy.rsiech;


import java.util.Base64;

public class Constants {
    public static final String GIST_LINK = "https://gist.githubusercontent.com/Piato4ka/01c832ac429ee527aa8153ffa5db6e47/raw/Egypt_Riches/";
    public static final String devKEyDice = "UnJvR3lITk5LaEdRSEVqNFJGUERDYw==";
    public static final String oneSignalKey = "Y2YzYTBlNzUtZjk2Yy00OWFkLWFmMTMtMDFkZmU3NTJmNzRi";


    public static String decode (String str) {
        byte[] decodedBytes = Base64.getDecoder().decode(str);
        return new String(decodedBytes);
    }
}
