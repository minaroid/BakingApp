package app.baking.example.bakingapp.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import app.baking.example.bakingapp.R;
import app.baking.example.bakingapp.models.Cake;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsAdapter extends RecyclerView.Adapter{

   private ArrayList<String> types = new ArrayList<>();
   private Cake cake ;

    public DetailsAdapter(Cake c ){
        this.types.add("Ingredients");
        this.types.add("Steps");
        this.cake = c;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        switch (viewType) {
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredients, parent, false);
                return new IngredientsViewHolder(view);

            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_steps, parent, false);
                return new StepsViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        switch (types.get(position)){
            case "Ingredients":
                RecyclerView re = ((IngredientsViewHolder) holder).ingredients_recycler_view;
                re.setAdapter(new IngredientsAdapter(cake));
                break;

            case "Steps":
                RecyclerView re2 = ((StepsViewHolder) holder).steps_recycler_view;
                re2.setAdapter(new StepsAdapter(cake));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return types.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (types.get(position)) {
            case "Ingredients":
                return 1;
            case "Steps":
                return 2;
            default:
                return -1;
        }
}

    class IngredientsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recycler_ingredient)
        RecyclerView ingredients_recycler_view;

        public IngredientsViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }
    }

    class StepsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recycler_step)
        RecyclerView steps_recycler_view;

        public StepsViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }
    }

}
