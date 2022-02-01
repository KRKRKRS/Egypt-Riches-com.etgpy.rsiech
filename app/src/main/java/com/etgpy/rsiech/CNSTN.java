package com.etgpy.rsiech;


public class CNSTN {
    public static final String DB_6_E_47_RAW_EGYPT_RICHES = "https://gist.githubusercontent.com/Piato4ka/01c832ac429ee527aa8153ffa5db6e47/raw/Egypt_Riches/";
    public static final String LA_ED_RSEVQ_NFJGUERDYW = "UnJvR3lITk5LaEdRSEVqNFJGUERDYw==";
    public static final String NTJM_NZ_RI = "Y2YzYTBlNzUtZjk2Yy00OWFkLWFmMTMtMDFkZmU3NTJmNzRi";



    public static String decode(String str) {
        byte[] decodedBytes = android.util.Base64.decode(str,android.util.Base64.URL_SAFE | android.util.Base64.NO_PADDING );
        return  new String (decodedBytes);
    }
}
