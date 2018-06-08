package app.baking.example.bakingapp.ui.fragments.homeFragment;

import java.util.ArrayList;

import app.baking.example.bakingapp.models.Cake;
import rx.Observable;

public interface HomeFragmentMVP {

    interface View {

//        void openDetailsFragment(Cake c);
        void showMessage(int msg);
        void notifyAdapter(ArrayList<Cake> cakes);
    }

    interface Presenter {

        void loadData();
        void setView(HomeFragmentMVP.View v);
        HomeFragmentMVP.View getView();
        void rxUnsubscribe();

    }

    interface Model {

        Observable<Cake> results();
    }
}
