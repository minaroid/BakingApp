package app.baking.example.bakingapp.ui.fragments.homeFragment;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import app.baking.example.bakingapp.R;
import app.baking.example.bakingapp.models.Cake;
import app.baking.example.bakingapp.rest.BakingAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragmentPresenter implements HomeFragmentMVP.Presenter {
    @Nullable
    private BakingAPI api;
    private HomeFragmentMVP.View view;

    public HomeFragmentPresenter(BakingAPI api){

       this.api = api;
    }


    @Override
    public void loadData() {
        Call<ArrayList<Cake>> call = api.getCakes();
        call.enqueue(new Callback<ArrayList<Cake>>() {
            @Override
            public void onResponse(Call<ArrayList<Cake>> call, Response<ArrayList<Cake>> response) {
               if(response.isSuccessful()){
                   if(response.body()!=null){
                       view.notifyAdapter(response.body());
                   }
               }
            }

            @Override
            public void onFailure(Call<ArrayList<Cake>> call, Throwable t) {
                t.printStackTrace();
                view.showMessage(0);
            }
        });
    }

    @Override
    public void setView(HomeFragmentMVP.View v) {
        this.view = v;
    }
}
