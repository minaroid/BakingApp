package app.baking.example.bakingapp.ui.fragments.homeFragment;

import java.util.ArrayList;

import app.baking.example.bakingapp.models.Cake;

public interface HomeFragmentMVP {

    interface View {

        void openDetailsFragment(Cake c);
        void showMessage(int msg);
        void notifyAdapter(ArrayList<Cake> cakes);
    }

    interface Presenter {

        void loadData();
        void setView(HomeFragmentMVP.View v);

    }

    interface Model {


    }
}
