package app.baking.example.bakingapp.ui.fragments.homeFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;
import javax.inject.Inject;
import app.baking.example.bakingapp.R;
import app.baking.example.bakingapp.models.Cake;
import app.baking.example.bakingapp.root.App;
import app.baking.example.bakingapp.ui.activities.HomeActivity;
import app.baking.example.bakingapp.ui.adapters.HomeAdapter;
import app.baking.example.bakingapp.ui.fragments.detailsFragment.DetailsFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment implements HomeFragmentMVP.View{

    private HomeAdapter adapter ;
    @BindView(R.id.recycler_home)
    RecyclerView recyclerView;
    @Inject
    HomeFragmentMVP.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getActivity().getApplication()).getComponent().injectHomeFragment(this);
        adapter = new HomeAdapter(getContext(),this);
        presenter.setView(this);
        presenter.loadData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        ((HomeActivity)getContext()).getSupportActionBar().setTitle(R.string.app_name);
        ((HomeActivity)getContext()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        if(((App)getActivity().getApplicationContext()).isTwoPane()){
            recyclerView.setLayoutManager(layoutManager());
        }
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.rxUnsubscribe();
    }

    @Override
    public void openDetailsFragment(Cake c) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle b = new Bundle();
        b.putSerializable("item",c);
        fragment.setArguments(b);
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(DetailsFragment.class.getSimpleName());
        ft.replace(R.id.fragment_container,fragment,DetailsFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void showMessage(int msg) {
        switch (msg){
            case 0 :
                Toast.makeText(getContext(),R.string.msg_loading_error,Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    public void notifyAdapter(ArrayList<Cake> cakes) {
        adapter.swapData(cakes);
    }

    public GridLayoutManager layoutManager(){
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density  = getResources().getDisplayMetrics().density;
        float dpWidth  = outMetrics.widthPixels / density;
        int columns = Math.round(dpWidth/300);
         return new GridLayoutManager(getActivity(),columns);
    }
}
