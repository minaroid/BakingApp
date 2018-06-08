package app.baking.example.bakingapp.ui.fragments.detailsFragment;

public interface DetailsFragmentMVP {

    interface view{

        void openRecipeActivity(String shortDes,String des,String vUrl);
        void showMessage(String msg);
    }
}
