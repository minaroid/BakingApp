package app.baking.example.bakingapp.ui.adapters;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.baking.example.bakingapp.R;
import app.baking.example.bakingapp.models.Cake;
import app.baking.example.bakingapp.models.Ingredient;
import app.baking.example.bakingapp.models.Step;
import app.baking.example.bakingapp.root.App;
import app.baking.example.bakingapp.ui.fragments.detailsFragment.DetailsFragmentMVP;
import app.baking.example.bakingapp.ui.fragments.detailsFragment.TwoPaneListener;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.MyViewHolder> {

    private ArrayList<Step> steps;
    private Fragment fragment;
    private TwoPaneListener paneListener;
    private DetailsFragmentMVP.view view;
    private Cake cake ;
    public StepsAdapter(Cake c, Fragment f){
        this.steps = c.getSteps();
        this.cake = c;
        this.fragment = f;
        this.paneListener = (TwoPaneListener) f;
        this.view =(DetailsFragmentMVP.view) f ;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_step, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.shortDes.setText(steps.get(position).getShortDescription());
        if(!steps.get(position).getDescription().equals("")){
            holder.desc.setText(steps.get(position).getDescription());
        }

        if(!steps.get(position).getThumbnailURL().equals("")){
            Glide.with(fragment.getContext())
                    .asBitmap()
                    .load(steps.get(position).getThumbnailURL())
                    .into(holder.stepImage);
        }
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_sh_des)
        TextView shortDes;
        @BindView(R.id.tv_des)
        TextView desc;
        @BindView(R.id.image_step)
        ImageView stepImage;

        public MyViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            this.itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = (int) v.getTag();
            if(((App)fragment.getContext().getApplicationContext()).isTwoPane()){
                paneListener.setVideoUrl("dxdfsd");
            }else{
                if(cake.getSteps().get(pos).getVideoURL().equals("")){
                  view.showMessage(fragment.getActivity().getString(R.string.msg_video_not_available));
                }
                else {
                    view.openRecipeActivity(cake.getSteps().get(pos).getShortDescription(),
                            cake.getSteps().get(pos).getDescription(),
                            cake.getSteps().get(pos).getVideoURL());
                }
            }
        }
    }
}
