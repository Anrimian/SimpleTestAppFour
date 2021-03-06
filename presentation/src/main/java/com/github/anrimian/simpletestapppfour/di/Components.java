package com.github.anrimian.simpletestapppfour.di;


import android.content.Context;

import com.github.anrimian.simpletestapppfour.di.app.AppComponent;
import com.github.anrimian.simpletestapppfour.di.app.AppModule;
import com.github.anrimian.simpletestapppfour.di.app.DaggerAppComponent;


/**
 * Created on 11.02.2017.
 */

public class Components {

    private static Components instance;

    private AppComponent appComponent;

    public static void init(Context appContext) {
        instance = new Components(appContext);
    }

    private static Components getInstance() {
        if (instance == null) {
            throw new IllegalStateException("components must be init first");
        }
        return instance;
    }

    private Components(Context appContext) {
        appComponent = buildAppComponent(appContext);
    }

    public static AppComponent getAppComponent() {
        return getInstance().appComponent;
    }

    private AppComponent buildAppComponent(Context appContext) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(appContext))
                .build();
    }

}
