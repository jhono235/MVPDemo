package examples.aaronhoskins.com.mvpdemo.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import examples.aaronhoskins.com.mvpdemo.R;
import examples.aaronhoskins.com.mvpdemo.model.randomuser.Result;

public class RandomUserRvAdapter extends RecyclerView.Adapter<RandomUserRvAdapter.ViewHolder> {
    List<Result> userResultsList;

    public RandomUserRvAdapter(List<Result> userResultsList) {
        this.userResultsList = userResultsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.random_user_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result currentResult = userResultsList.get(position);
        holder.tvEmail.setText(currentResult.getEmail());
        holder.tvName.setText(currentResult.getName().toString());
        Glide
                .with(holder.itemView)
                .load(currentResult.getPicture().getThumbnail())
                .into(holder.imgThumbnail);
    }

    @Override
    public int getItemCount() {
        return userResultsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvEmail;
        ImageView imgThumbnail;
        public ViewHolder(View itemView) {
            super(itemView);

            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvName  = itemView.findViewById(R.id.tvName);
            imgThumbnail = itemView.findViewById(R.id.imgUserThumbnail);
        }
    }
}
