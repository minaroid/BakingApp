package app.baking.example.bakingapp.root;



import javax.inject.Singleton;

import app.baking.example.bakingapp.rest.ApiModule;
import app.baking.example.bakingapp.ui.activities.HomeActivity;
import app.baking.example.bakingapp.ui.fragments.homeFragment.HomeFragment;
import app.baking.example.bakingapp.ui.fragments.homeFragment.HomeFragmentModule;
import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class , HomeFragmentModule.class})
public interface ApplicationComponent {

    void injectHomeFragment(HomeFragment target);

}
