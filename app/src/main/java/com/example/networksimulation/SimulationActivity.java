package com.example.networksimulation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SimulationActivity extends AppCompatActivity implements ItemClickListener {

    private List<Device> mDeviceList;
    private RecyclerView mRecycler;
    private MyAdapter mAdapter;

    private RelativeLayout mLayout;
    private ImageView mNewDeviceImage;
    private int xDelta;
    private int yDelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation);

        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));

        mDeviceList = new ArrayList<>();
        mDeviceList.add(new Device(1, "Computer", 5, R.drawable.ic_desktop));
        mDeviceList.add(new Device(2, "Firewall", 4, R.drawable.ic_security));
        mDeviceList.add(new Device(3, "Router", 3, R.drawable.ic_router));
        mDeviceList.add(new Device(4, "Switch", 2, R.drawable.ic_videogame));


        mAdapter = new MyAdapter(this, mDeviceList);
        mRecycler.setAdapter(mAdapter);
        mAdapter.setClickListener(this);

        mLayout = (RelativeLayout) findViewById(R.id.my_relative_layout);

    }

    @Override
    public void onItemClick(View view, int position) {
        final Device device = mDeviceList.get(position);
        addDevice(device.getImageId());
    }

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();

            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                    xDelta = X - lParams.leftMargin;
                    yDelta = Y - lParams.topMargin;
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                    layoutParams.leftMargin = X - xDelta;
                    layoutParams.topMargin = Y - yDelta;
                    layoutParams.rightMargin = -250;
                    layoutParams.bottomMargin = -250;
                    v.setLayoutParams(layoutParams);
                    break;
            }
            mLayout.invalidate();
            return true;
        }
    };

    private void addDevice(int imageId) {
        mNewDeviceImage = new ImageView(SimulationActivity.this);
        mNewDeviceImage.setImageResource(imageId);
        mNewDeviceImage.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));
        mNewDeviceImage.setOnTouchListener(onTouchListener);
        mLayout.addView(mNewDeviceImage);
    }
}
