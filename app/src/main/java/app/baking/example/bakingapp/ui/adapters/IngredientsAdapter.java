package app.baking.example.bakingapp.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import app.baking.example.bakingapp.R;
import app.baking.example.bakingapp.models.Cake;
import app.baking.example.bakingapp.models.Ingredient;
import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.MyViewHolder> {
    private ArrayList<Ingredient> ingredients;
    public IngredientsAdapter(Cake c){

        this.ingredients = c.getIngredients();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredient, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.ingredient.setText(ingredients.get(position).getIngredient());
        holder.quantity.setText(String.valueOf(ingredients.get(position).getQuantity()));
        holder.measuring.setText(ingredients.get(position).getMeasure());
    }

    @Override
    public int getItemCount() {

        return ingredients.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_ingre)
        TextView ingredient;
        @BindView(R.id.tv_quantity)
        TextView quantity;
        @BindView(R.id.tv_measuring)
        TextView measuring;

        public MyViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }
    }
}
