package com.etgpy.rsiech;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GistApi {

    @GET(CNSTN.DB_6_E_47_RAW_EGYPT_RICHES)
    Call<ResponseBody> getStringUrl();
}
