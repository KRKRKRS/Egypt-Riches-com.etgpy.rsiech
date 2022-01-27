package com.example.firstapp1;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GistApi {

    @GET(Constants.GIST_LINK)
    Call<ResponseBody> getStringUrl();
}
