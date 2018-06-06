package app.baking.example.bakingapp.ui.fragments.homeFragment;

import android.app.Application;
import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.baking.example.bakingapp.rest.BakingAPI;
import dagger.Module;
import dagger.Provides;

@Module
public class HomeFragmentModule {

    @Inject
    BakingAPI api;



    @Provides
    public HomeFragmentMVP.Presenter provideLoginActivityPresenter(BakingAPI api ) {
        return new HomeFragmentPresenter(api);
    }
}
