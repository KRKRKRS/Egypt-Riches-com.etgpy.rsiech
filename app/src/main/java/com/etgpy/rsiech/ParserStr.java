package com.etgpy.rsiech;


import static com.etgpy.rsiech.ApplicationClass.AFId;
import static com.etgpy.rsiech.F_B_K.AIDetgpy;

import com.onesignal.OneSignal;

public class ParserStr {
    String[] keys = {CNSTN.decode("Pw=="), CNSTN.decode("JnN1YjY9"), CNSTN.decode("JnN1Yjc9"), CNSTN.decode("JnN1YjI9"), CNSTN.decode("JnN1YjM9"), CNSTN.decode("JnN1YjQ9"), CNSTN.decode("JnN1YjU9")};


    String parse(String input) {
        String[] params = input.split("::");
        StringBuilder result = new StringBuilder();
        result.append(params[0]).append("?")
                .append(CNSTN.decode("YnVuZGxlPQ==")).append("com.etgpy.rsiech")
                .append(CNSTN.decode("JmFkX2lkPQ==")).append(AIDetgpy)
                .append(CNSTN.decode("JmFwcHNfaWQ9")).append(AFId)
                .append(CNSTN.decode("JmRldl9rZXk9")).append(CNSTN.decode(CNSTN.LA_ED_RSEVQ_NFJGUERDYW));

        for (int i = 1; i < params.length; i++) {
            result.append(keys[i]).append(params[i]);
        }

        String teamName = params[1];
        OneSignal.sendTag(CNSTN.decode("c3ViX2FwcA=="), teamName);
        return String.valueOf(result);
    }

}

