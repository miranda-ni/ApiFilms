package com.example.apifilms;

import android.app.Application;

import com.example.apifilms.data.netwokr.GhibliService;

public class App extends Application {

    public static GhibliService ghibliService;

    @Override
    public void onCreate() {
        super.onCreate();

        ghibliService = new GhibliService();

    }
}
