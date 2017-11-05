package com.s4m.emc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.s4m.emc.R;
import com.s4m.emc.model.EMCData;
import com.s4m.emc.api.EMCDataClickListener;
import java.util.List;

public class EMCDataAdapter extends RecyclerView.Adapter<EMCDataAdapter.ViewHolder> {
    private List<EMCData> emcDataList;
    private EMCDataClickListener clickListener;
    EMCData emcDataItem;
    Context context;

    // Constructors
    public EMCDataAdapter(List<EMCData> emcDataList, Context context) {
        this.emcDataList = emcDataList;
        this.context = context;
    }

    @Override
    public EMCDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EMCDataAdapter.ViewHolder viewHolder, int position) {

        emcDataItem = emcDataList.get(position);

        viewHolder.announcementTitle.setText(emcDataItem.getAnnouncementTitle().getTitleValue());
        Glide.with(context).load(emcDataItem.getAnnouncementImage().getImageUrl().toLowerCase().trim()).fitCenter().placeholder(R.mipmap.ic_launcher).dontAnimate().into(viewHolder.announcementImage);
        viewHolder.announcementHtml.setText(emcDataItem.getAnnouncementDescription().getDescriptionValue());
    }

    @Override
    public int getItemCount() {
        return emcDataList.size();
    }

    public void setClickListener(EMCDataClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView announcementImage;
        private TextView announcementTitle;
        private TextView announcementHtml;
        public ViewHolder(View view) {
            super(view);
            announcementImage = (ImageView)view.findViewById(R.id.announcement_image);
            announcementTitle = (TextView)view.findViewById(R.id.announcement_title);
            announcementHtml = (TextView)view.findViewById(R.id.announcement_html);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onItemClick(getAdapterPosition(), emcDataItem);
        }
    }
}