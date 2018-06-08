package app.baking.example.bakingapp.ui.fragments.homeFragment;

import java.util.ArrayList;
import app.baking.example.bakingapp.models.Cake;
import app.baking.example.bakingapp.rest.BakingAPI;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeFragmentPresenter implements HomeFragmentMVP.Presenter {

    private HomeFragmentMVP.View view;
    private BakingAPI api;
    private Subscription subscription = null;

    public HomeFragmentPresenter(BakingAPI api){
       this.api = api;
    }

    @Override
    public void setView(HomeFragmentMVP.View v) {
        this.view = v;
    }

    @Override
    public void loadData() {
        subscription = api.getCakes().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<Cake>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.showMessage(0);
                e.printStackTrace();
            }

            @Override
            public void onNext(ArrayList<Cake> c) {
                view.notifyAdapter(c);
            }
        });
    }

    @Override
    public void rxUnsubscribe() {
       if(view != null){
           if(!subscription.isUnsubscribed()){
               subscription.unsubscribe();
           }
       }
    }
    @Override
    public HomeFragmentMVP.View getView() {
        return view;
    }
}
