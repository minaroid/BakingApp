package app.baking.example.bakingapp.root;

import android.app.Application;


import app.baking.example.bakingapp.rest.ApiModule;
import app.baking.example.bakingapp.ui.fragments.homeFragment.HomeFragmentModule;

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .apiModule(new ApiModule())
                .homeFragmentModule(new HomeFragmentModule())
                .build();
    }

    public ApplicationComponent getComponent() {

        return component;
    }
}
