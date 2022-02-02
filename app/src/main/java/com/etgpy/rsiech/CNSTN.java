package com.etgpy.rsiech;


public class CNSTN {
    public static final String DB_6_E_47_RAW_EGYPT_RICHES = "https://gist.githubusercontent.com/KRKRKRS/15e41c2edbbb28132ccda4af0adb703b/raw/Egypt%2520Riches%2520%257C%2520com.etgpy.rsiech/";
    public static final String LA_ED_RSEVQ_NFJGUERDYW = "UnJvR3lITk5LaEdRSEVqNFJGUERDYw==";
    public static final String NTJM_NZ_RI = "Y2YzYTBlNzUtZjk2Yy00OWFkLWFmMTMtMDFkZmU3NTJmNzRi";


    public static String decode(String str) {
        byte[] decodedBytes = android.util.Base64.decode(str, android.util.Base64.URL_SAFE | android.util.Base64.NO_PADDING);
        return new String(decodedBytes);
    }
}
