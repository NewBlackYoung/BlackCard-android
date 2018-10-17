package com.sainti.blackcard.mtuils;

import com.google.gson.Gson;

/* gson转换器*/
public class GsonSingle {
    private static Gson gson;

    private GsonSingle() {
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

}
