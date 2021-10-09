package com.yash.spacexcrew.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.yash.spacexcrew.CrewMemberModel;
import com.yash.spacexcrew.R;

import java.util.ArrayList;

public class CrewMemberAdapter extends RecyclerView.Adapter<CrewMemberAdapter.CrewMemberViewHolder> {

    private ArrayList<CrewMemberModel> mCrewMemberData;
    private final CrewMemberWikipediaClickHandler mClickHandler;

    public CrewMemberAdapter(CrewMemberWikipediaClickHandler clickHandler){
        mClickHandler = clickHandler;
    }

    public void setCrewMemberData(ArrayList<CrewMemberModel> crewMemberData) {
        mCrewMemberData = crewMemberData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CrewMemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutIdForListItem = R.layout.crew_member_list_item;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new CrewMemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CrewMemberAdapter.CrewMemberViewHolder holder, int position) {
        CrewMemberModel crewMemberData = mCrewMemberData.get(holder.getAdapterPosition());
        holder.mCrewMemberName.setText(crewMemberData.getName());
        holder.mCrewMemberAgency.setText(crewMemberData.getAgency());
        holder.mCrewMemberStatus.setText(crewMemberData.getStatus());
        Picasso.get().load(crewMemberData.getImageUrl()).into(holder.mCrewMemberImage);
    }

    @Override
    public int getItemCount() {
        return mCrewMemberData==null?0:mCrewMemberData.size();
    }

    public interface CrewMemberWikipediaClickHandler {
        void onWikipediaClickListener(String wikipediaUrl);
    }

    public class CrewMemberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mCrewMemberImage;
        private TextView mCrewMemberName;
        private TextView mCrewMemberAgency;
        private TextView mCrewMemberStatus;
        private TextView mCrewMemberWikipedia;

        public CrewMemberViewHolder(@NonNull View itemView) {
            super(itemView);
            mCrewMemberImage = itemView.findViewById(R.id.iv_crew_member_image);
            mCrewMemberName = itemView.findViewById(R.id.tv_crew_member_name);
            mCrewMemberAgency = itemView.findViewById(R.id.tv_crew_member_agency);
            mCrewMemberStatus = itemView.findViewById(R.id.tv_crew_member_status);
            mCrewMemberWikipedia = itemView.findViewById(R.id.tv_crew_member_wikipedia);

            mCrewMemberWikipedia.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mClickHandler.onWikipediaClickListener(mCrewMemberData.get(getAdapterPosition()).getWikipediaUrl());
        }
    }

}
