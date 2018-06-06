package app.baking.example.bakingapp.rest;

import java.util.ArrayList;

import app.baking.example.bakingapp.models.Cake;
import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

public interface BakingAPI {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Observable<ArrayList<Cake>> getCakes();


}
