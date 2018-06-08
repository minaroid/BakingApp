package app.baking.example.bakingapp.ui.activities.homeActivity;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import app.baking.example.bakingapp.R;
import app.baking.example.bakingapp.models.Cake;
import app.baking.example.bakingapp.root.App;
import app.baking.example.bakingapp.ui.fragments.detailsFragment.DetailsFragment;
import app.baking.example.bakingapp.ui.fragments.homeFragment.HomeFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeActivityMVP.View{

    @BindView(R.id.toolbar_id)
    Toolbar toolbar;
    private static final String homeFragmentTAG= HomeFragment.class.getSimpleName();
    private static final String detailsFragmentTAG= DetailsFragment.class.getSimpleName();
    private FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if(findViewById(R.id.two_pane_view)!=null){
            ((App)getApplicationContext()).setTwoPane(true);
        }

        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment f = fm.findFragmentByTag(homeFragmentTAG);
        if(savedInstanceState == null && f == null){
            ft.addToBackStack(homeFragmentTAG);
            ft.replace(R.id.fragment_container,new HomeFragment(),homeFragmentTAG)
                   .commit();
        }
        else if(f != null){
            fm.beginTransaction()
                    .replace(R.id.fragment_container,
                            fm.findFragmentByTag(homeFragmentTAG),
                            homeFragmentTAG).commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Fragment f = fm.findFragmentByTag(detailsFragmentTAG);
        if(f!=null){
         outState.putString("details","d");
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
            if(savedInstanceState != null){
                if(savedInstanceState.getString("details") != null){
                    fm.beginTransaction()
                            .replace(R.id.fragment_container,
                                    fm.findFragmentByTag(detailsFragmentTAG),
                                    detailsFragmentTAG).commit();
            }}
    }

    @Override
    public void onBackPressed() {
        if (fm.findFragmentByTag(homeFragmentTAG).isAdded()) {
            finish();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void openDetailsFragment(Cake c) {

        DetailsFragment fragment = new DetailsFragment();
        Bundle b = new Bundle();
        b.putSerializable("item",c);
        fragment.setArguments(b);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(DetailsFragment.class.getSimpleName());
        ft.replace(R.id.fragment_container,fragment,DetailsFragment.class.getSimpleName())
                .commit();

    }
}
