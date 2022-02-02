package com.etgpy.rsiech;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.applinks.AppLinkData;

public class F_B_K {
    public static String strDeepetgpy;
    public static String AIDetgpy;

    F_B_K(String fbId, ER mainActivity) {
        FacebookSdk.setApplicationId(fbId);
        FacebookSdk.setAdvertiserIDCollectionEnabled(true);
        FacebookSdk.sdkInitialize(mainActivity.getApplicationContext());
        FacebookSdk.fullyInitialize();
        FacebookSdk.setAutoInitEnabled(true);
        FacebookSdk.setAutoLogAppEventsEnabled(true);

        AppEventsLogger.activateApp(mainActivity.getApplication());

        AppLinkData.fetchDeferredAppLinkData(mainActivity.getApplication(),
                new AppLinkData.CompletionHandler() {
                    @Override
                    public void onDeferredAppLinkDataFetched(AppLinkData appLinkData) {
                        String deepLink = appLinkData.getTargetUri().getQuery();
                        ParserStr parserStr = new ParserStr();
                        do {
                        } while (AIDetgpy == null);
                        strDeepetgpy = parserStr.parse(deepLink);
                    }
                }
        );
    }
}
