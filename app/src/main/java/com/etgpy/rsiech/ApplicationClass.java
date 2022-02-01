package com.etgpy.rsiech;

import android.app.Application;
import android.util.Log;

import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.onesignal.OneSignal;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;


public class ApplicationClass extends Application {
    private static final String ONESIGNAL_APP_ID = Constants.decode(Constants.oneSignalKey);
    public static final String APPS_FLYER_KEY = Constants.decode(Constants.devKEyDice);
    public static String appsFlyerId;
    public static boolean appsFlyerLoaded;

    @Override
    public void onCreate() {
        super.onCreate();
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);


        new Thread(new Runnable() {
            public void run() {
                try {
                    FaceBook.AID = AdvertisingIdClient.getAdvertisingIdInfo(getBaseContext()).getId();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                }
            }
    }).start();


        AppsFlyerLib.getInstance().init(APPS_FLYER_KEY, new MyAppsFlyerConversionListener(), this);
        AppsFlyerLib.getInstance().start(this);
        appsFlyerId = AppsFlyerLib.getInstance().getAppsFlyerUID(this);
    }


    class MyAppsFlyerConversionListener implements AppsFlyerConversionListener {
        @Override
        public void onConversionDataSuccess(Map<String, Object> conversionDataMap) {
            for (String attrName : conversionDataMap.keySet())
                Log.i("MyApp", "Conversion attribute: " + attrName + " = " + conversionDataMap.get(attrName));

            ER.statusAppsFlyer = Objects.requireNonNull(conversionDataMap.get(Constants.decode("YWZfc3RhdHVz")) ).toString();
            if (ER.statusAppsFlyer.equals("Non-organic")) {
                String campaignStr = Objects.requireNonNull(conversionDataMap.get(Constants.decode("Y2FtcGFpZ24="))).toString();
                //нейминг
                //sub6::sub7::keyCompany::sub2::sub3::sub1::sub4::sub5
                Log.i("MyApp", "campaignStr " + campaignStr);
                ParserStr parserStr = new ParserStr();
                ER.strAppsFlyer = parserStr.parse(campaignStr);
            }
            appsFlyerLoaded = true;
        }

        public void onConversionDataFail(String errorMessage) { }

        public void onAppOpenAttribution(Map<String, String> attributionData) { }

        public void onAttributionFailure(String errorMessage) { }
    }
}

