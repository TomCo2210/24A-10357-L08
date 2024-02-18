package dev.tomco.a24a_10357_l08;

import android.app.Application;

import dev.tomco.a24a_10357_l08.Utilities.ImageLoader;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoader.initImageLoader(this);
    }
}
