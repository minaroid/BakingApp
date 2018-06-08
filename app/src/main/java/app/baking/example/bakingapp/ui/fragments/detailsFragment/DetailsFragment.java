package app.baking.example.bakingapp.ui.fragments.detailsFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import app.baking.example.bakingapp.R;
import app.baking.example.bakingapp.models.Cake;
import app.baking.example.bakingapp.ui.activities.HomeActivity;
import app.baking.example.bakingapp.ui.activities.RecipeActivity;
import app.baking.example.bakingapp.ui.adapters.DetailsAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsFragment extends Fragment implements
        TwoPaneListener , DetailsFragmentMVP.view{
    @BindView(R.id.recycler_details)
    RecyclerView recyclerView;

    private DetailsAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        Cake cake = (Cake) getArguments().getSerializable("item");
        ((HomeActivity)getContext()).getSupportActionBar().setTitle(cake.getName());
        ((HomeActivity)getContext()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adapter = new DetailsAdapter(cake,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case android.R.id.home :
                getActivity().onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setVideoUrl(String url) {
        Toast.makeText(getActivity(),url,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openRecipeActivity(String shortDes, String des, String vUrl) {

        Intent i = new Intent(getActivity(), RecipeActivity.class);
        i.putExtra("shortDes",shortDes);
        i.putExtra("des",des);
        i.putExtra("vUrl",vUrl);
        startActivity(i);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

}
