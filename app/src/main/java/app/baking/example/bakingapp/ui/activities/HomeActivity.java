package app.baking.example.bakingapp.ui.activities;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import app.baking.example.bakingapp.R;
import app.baking.example.bakingapp.root.App;
import app.baking.example.bakingapp.ui.fragments.homeFragment.HomeFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_id)
    Toolbar toolbar;
    private FragmentManager fm;
    private static final String HomeFragmentTAG= HomeFragment.class.getSimpleName();

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
        ft.addToBackStack(HomeFragmentTAG);
        ft.replace(R.id.fragment_container,new HomeFragment(),HomeFragmentTAG)
               .commit();
    }


    @Override
    public void onBackPressed() {
        if (fm.findFragmentByTag(HomeFragmentTAG).isAdded()) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

}
