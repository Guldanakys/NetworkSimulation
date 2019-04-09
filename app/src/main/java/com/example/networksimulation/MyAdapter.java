package com.example.networksimulation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Device> mList;
    private Context mContext;
    private ItemClickListener mClickListener;

    public MyAdapter(Context context, List<Device> list) {
        mList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        Device device = mList.get(i);
        viewHolder.bind(device);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setClickListener(ItemClickListener clickListener) {
        mClickListener = clickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mNameText;
        private ImageView mImageView;
        private Device mDevice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            mNameText = itemView.findViewById(R.id.device_name);
            mImageView = itemView.findViewById(R.id.device_image);
        }

        public void bind(Device device) {
            mDevice = device;
            mNameText.setText(mDevice.getName());
            mImageView.setImageResource(mDevice.getImageId());
        }

        @Override
        public void onClick(View v) {
            mClickListener.onItemClick(v, getAdapterPosition());
        }
    }
}
