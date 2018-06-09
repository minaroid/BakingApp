package app.baking.example.bakingapp.ui.activities.homeActivity;

import app.baking.example.bakingapp.models.Cake;

public interface HomeActivityMVP {


    interface View{

        void openDetailsFragment(Cake c);
        void openRecipeActivity(String shortDes, String des, String vUrl);
    }
}
