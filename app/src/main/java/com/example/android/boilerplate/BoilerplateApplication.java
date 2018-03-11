package com.example.android.boilerplate;

import android.app.Application;

import com.example.android.boilerplate.di.DaggerDataComponent;
import com.example.android.boilerplate.di.DataComponent;
import com.example.android.boilerplate.di.DataModule;

/**
 * Created by carolinamarin on 2/3/18.
 */

public class BoilerplateApplication extends Application {

    private static BoilerplateApplication app;
    DataComponent dataComponent;

    public static BoilerplateApplication getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;

        initDataComponent();

        dataComponent.inject(this);
    }

    private void initDataComponent(){
        dataComponent = DaggerDataComponent.builder()
                .dataModule(new DataModule(this))
                .build();
    }

    public DataComponent getDataComponent() {
        return dataComponent;
    }
}
