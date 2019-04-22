package com.example.networksimulation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CommandAdapter extends RecyclerView.Adapter<CommandAdapter.CommandViewHolder> {

    private List<Command> mCommandList;

    private Context mContext;

    private ItemClickListener mClickListener;

    public CommandAdapter(Context context, List<Command> commandList) {
        mContext = context;
        mCommandList = commandList;
    }

    @NonNull
    @Override
    public CommandViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.command_list_item, viewGroup, false);
        return new CommandViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommandViewHolder commandViewHolder, int i) {
        Command command = mCommandList.get(i);
        commandViewHolder.bind(command);
    }

    @Override
    public int getItemCount() {
        return mCommandList.size();
    }

    public void setClickListener(ItemClickListener clickListener) {
        mClickListener = clickListener;
    }

    class CommandViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTitle;
        private Command mCommand;

        public CommandViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            mTitle = (TextView) itemView.findViewById(R.id.command_title);
        }

        public void bind(Command command) {
            mCommand = command;
            mTitle.setText(mCommand.getTitle());
        }

        @Override
        public void onClick(View v) {
            mClickListener.onCommandItemClick(v, getAdapterPosition());
        }
    }
}
