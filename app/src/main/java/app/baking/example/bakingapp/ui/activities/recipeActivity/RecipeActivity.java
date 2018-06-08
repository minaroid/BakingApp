package app.baking.example.bakingapp.ui.activities.recipeActivity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import app.baking.example.bakingapp.R;
import app.baking.example.bakingapp.ui.fragments.recipeFragmnet.RecipeFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_id)
    Toolbar toolbar;
    private FragmentManager fm;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        intent = getIntent();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(intent.getStringExtra("shortDes"));

        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        RecipeFragment recipeFragment = new RecipeFragment();
        Bundle b =new Bundle();
        b.putString("des",intent.getStringExtra("shortDes"));
        b.putString("vUrl",intent.getStringExtra("vUrl"));
        recipeFragment.setArguments(b);
        ft.addToBackStack(RecipeFragment.class.getSimpleName());
        ft.replace(R.id.rec_container,recipeFragment,RecipeFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
