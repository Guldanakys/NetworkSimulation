package com.example.networksimulation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CommandLineAdapter extends RecyclerView.Adapter<CommandLineAdapter.CommandLineViewHolder> {

    private List<String> mCommandLineList;

    public CommandLineAdapter(List<String> commandLineList) {
        mCommandLineList = commandLineList;
    }

    @NonNull
    @Override
    public CommandLineViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.command_line_item, viewGroup, false);
        return new CommandLineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommandLineViewHolder commandLineViewHolder, int i) {
        commandLineViewHolder.mCommandLine.setText(mCommandLineList.get(i));
    }

    @Override
    public int getItemCount() {
        return mCommandLineList.size();
    }

    class CommandLineViewHolder extends RecyclerView.ViewHolder {

        private TextView mCommandLine;

        public CommandLineViewHolder(@NonNull View itemView) {
            super(itemView);

            mCommandLine = itemView.findViewById(R.id.command_line_title);
        }
    }
}
