package com.example.internshipscreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class InternshipAdapter extends RecyclerView.Adapter<InternshipAdapter.InternshipViewHolder> {

    private Context context;
    private List<Internship> internshipList;

    public InternshipAdapter(Context context, List<Internship> internshipList) {
        this.context = context;
        this.internshipList = internshipList;
    }

    @NonNull
    @Override
    public InternshipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_internship, parent, false);
        return new InternshipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InternshipViewHolder holder, int position) {
        Internship internship = internshipList.get(position);

        holder.tvTitle.setText(internship.getTitle());
        holder.tvCompany.setText(internship.getCompany());
        holder.tvApplicants.setText(internship.getApplicants() + " applicants");
        holder.tvDetails.setText(internship.getDetails());

        // Load image using Picasso or any image loader
        if (internship.getImageUrl() != null && !internship.getImageUrl().isEmpty()) {
            Picasso.get()
                    .load(internship.getImageUrl())
                    .placeholder(R.drawable.ic_placeholder)
                    .into(holder.imageView);
        } else {
            holder.imageView.setImageResource(R.drawable.ic_placeholder);
        }
    }

    @Override
    public int getItemCount() {
        return internshipList.size();
    }

    public void updateList(List<Internship> filteredList) {
        internshipList = filteredList;
        notifyDataSetChanged();
    }

    public static class InternshipViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvTitle, tvCompany, tvApplicants, tvDetails;

        public InternshipViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.internshipImage); // Make sure ID matches your XML
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvCompany = itemView.findViewById(R.id.tvCompany);
            tvApplicants = itemView.findViewById(R.id.tvApplicants); // Add this ID in XML
            tvDetails = itemView.findViewById(R.id.tvDetails);
        }
    }
}
