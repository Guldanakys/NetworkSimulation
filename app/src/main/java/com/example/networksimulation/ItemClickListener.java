package com.example.networksimulation;

import android.view.View;

public interface ItemClickListener {

    void onItemClick(View view, int position);

    void onCommandItemClick(View view, int position);
}
