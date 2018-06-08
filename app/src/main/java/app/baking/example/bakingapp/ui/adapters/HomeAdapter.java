package app.baking.example.bakingapp.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;

import app.baking.example.bakingapp.R;
import app.baking.example.bakingapp.models.Cake;
import app.baking.example.bakingapp.ui.activities.homeActivity.HomeActivityMVP;
import app.baking.example.bakingapp.ui.fragments.homeFragment.HomeFragmentMVP;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.CakeViewHolder>
implements Serializable {

    private ArrayList<Cake> cakes = new ArrayList<>();
    private Context context ;
    private HomeActivityMVP.View activityView ;
    public HomeAdapter(Context c ){
        this.context = c;
        this.activityView = (HomeActivityMVP.View) c ;
    }


    @NonNull
    @Override
    public CakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(context).inflate(R.layout.item_cake, parent, false);
        return new CakeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CakeViewHolder holder, int position) {
      Cake c = cakes.get(position);
      holder.cakeTitle.setText(c.getName());
      holder.cakeServing.setText( context.getString(R.string.cake_servings)+"  "+ c.getServings() );

      if (!c.getImage().equals("")) {
          Glide.with(context)
                  .load(c.getImage())
                  .into(holder.cakeImage);
      }

      holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        if (cakes == null) {
            return 0;
        } else {
            return cakes.size();
        }
    }

    public void swapData(ArrayList<Cake> c){
        cakes.clear();
        cakes.addAll(c);
        notifyDataSetChanged();
    }

    class CakeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.image_cake)
        ImageView cakeImage;
        @BindView(R.id.tv_cakeName)
        TextView cakeTitle;
        @BindView(R.id.tv_cake_serving)
        TextView cakeServing;

        public CakeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            activityView.openDetailsFragment(cakes.get((int) v.getTag()));
        }
    }
}