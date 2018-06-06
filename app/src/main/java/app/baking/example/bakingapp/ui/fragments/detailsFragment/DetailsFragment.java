package app.baking.example.bakingapp.ui.fragments.detailsFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import app.baking.example.bakingapp.R;
import app.baking.example.bakingapp.models.Cake;
import app.baking.example.bakingapp.ui.activities.HomeActivity;
import app.baking.example.bakingapp.ui.adapters.DetailsAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsFragment extends Fragment {
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
        View v = inflater.inflate(R.layout.fragment_details, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        Cake cake = (Cake) getArguments().getSerializable("item");
        ((HomeActivity)getContext()).getSupportActionBar().setTitle(cake.getName());
        ((HomeActivity)getContext()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adapter = new DetailsAdapter(cake);
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

}
