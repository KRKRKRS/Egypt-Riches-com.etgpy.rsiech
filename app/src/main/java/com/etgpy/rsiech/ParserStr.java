package com.etgpy.rsiech;


import static com.etgpy.rsiech.ApplicationClass.appsFlyerId;

import static com.etgpy.rsiech.Constants.devKEyDice;
import static com.etgpy.rsiech.FaceBook.AID;
import static com.example.firstapp1.BuildConfig.APPLICATION_ID;

import com.onesignal.OneSignal;

public class ParserStr {
    String[] keys = {"?", "&sub6=", "&sub7=", "&sub2=", "&sub3=", "&sub4=", "&sub5="};


    String parse(String input) {
        String[] params = input.split("::");
        StringBuilder result = new StringBuilder();
        result.append(params[0]).append("?")
                .append("bundle=" + APPLICATION_ID)
                .append("&ad_id=").append(AID)
                .append("&apps_id=").append(appsFlyerId)
                .append("&dev_key=").append(devKEyDice);

        for (int i = 1; i < params.length; i++) {
            result.append(keys[i]).append(params[i]);
        }

        String teamName = params[1];
        OneSignal.sendTag("sub_app", teamName);
        return String.valueOf(result);
    }

}

