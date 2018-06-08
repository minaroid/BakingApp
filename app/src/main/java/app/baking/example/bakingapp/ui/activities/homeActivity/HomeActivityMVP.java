package app.baking.example.bakingapp.ui.activities.homeActivity;

import app.baking.example.bakingapp.models.Cake;

public interface HomeActivityMVP {


    interface View{

        void openDetailsFragment(Cake c);
    }
}
