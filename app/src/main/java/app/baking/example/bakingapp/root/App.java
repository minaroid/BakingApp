package app.baking.example.bakingapp.root;

import android.app.Application;
import android.util.Log;


import app.baking.example.bakingapp.rest.ApiModule;
import app.baking.example.bakingapp.ui.fragments.homeFragment.HomeFragmentModule;

public class App extends Application {

    private ApplicationComponent component;
    private boolean isTwoPane = false;

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

    public boolean isTwoPane() {
        Log.d("IS_TWO_PANE", String.valueOf(isTwoPane));
        return isTwoPane;
    }

    public void setTwoPane(boolean twoPane) {
        isTwoPane = twoPane;
    }
}
