package com.etgpy.rsiech;


import static com.etgpy.rsiech.ApplicationClass.appsFlyerId;

import static com.etgpy.rsiech.Constants.devKEyDice;
import static com.etgpy.rsiech.FaceBook.AID;
import static com.example.firstapp1.BuildConfig.APPLICATION_ID;

import android.util.Log;

import com.onesignal.OneSignal;

public class ParserStr {
    String[] keys = {"?", "&sub6=", "&sub7=", "&sub2=", "&sub3=", "&sub4=", "&sub5="};


    String parse(String input) {
        String[] params = input.split("::");
        StringBuilder result = new StringBuilder();
        result.append(params[0]).append("?")
                .append(Constants.decode("YnVuZGxlPQ==")).append(APPLICATION_ID)
                .append(Constants.decode("JmFkX2lkPQ==")).append(AID)
                .append(Constants.decode("JmFwcHNfaWQ9")).append(appsFlyerId)
                .append(Constants.decode("JmRldl9rZXk9")).append(Constants.decode(Constants.devKEyDice));

        for (int i = 1; i < params.length; i++) {
            result.append(keys[i]).append(params[i]);
        }

        String teamName = params[1];
        OneSignal.sendTag(Constants.decode("c3ViX2FwcA=="), teamName);
        Log.i("MyApp", "parse str - " + String.valueOf(result));
        return String.valueOf(result);
    }

}

