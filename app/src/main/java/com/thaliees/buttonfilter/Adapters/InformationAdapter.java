package com.thaliees.buttonfilter.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thaliees.buttonfilter.Models.InformationModel;
import com.thaliees.buttonfilter.R;

import java.util.List;

public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.ViewHolder> {
    private Context context;
    private List<InformationModel> informationList;

    public InformationAdapter(List<InformationModel> informationList) {
        this.informationList = informationList;
    }

    @NonNull
    @Override
    public InformationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Save the context (Only if you need to use it in other methods of this class)
        context = viewGroup.getContext();
        // Create a new view with the layout indicated
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.info_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        // Replace the contents of a view for each position
        // Search the image to show
        // If the info contain a image, show
        if (!informationList.get(position).image.equals("")) {
            int id = context.getResources().getIdentifier(informationList.get(position).image, "drawable", context.getPackageName());
            viewHolder.getImage().setImageResource(id);
        }
        else viewHolder.getImage().setVisibility(View.GONE);
        // Set data
        viewHolder.getTitle().setText(informationList.get(position).title);
        viewHolder.getDate().setText(informationList.get(position).date);
        viewHolder.getDescription().setText(informationList.get(position).description);
    }

    @Override
    public int getItemCount() {
        return informationList.size();
    }

    // Creation of class that provide a reference to the type of views that you are using
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title, date, description;
        private final ImageView image;

        // Define the elements and the events (if exist) of the ViewHolder's View
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            description = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.image);
        }

        // Define get for each element

        public TextView getTitle() {
            return title;
        }

        public TextView getDate() {
            return date;
        }

        public TextView getDescription() {
            return description;
        }

        public ImageView getImage() {
            return image;
        }
    }
}
