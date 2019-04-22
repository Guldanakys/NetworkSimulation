package com.example.networksimulation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SimulationActivity extends AppCompatActivity implements ItemClickListener {

    private List<Device> mDeviceList;
    private RecyclerView mRecycler;
    private MyAdapter mAdapter;

    private RelativeLayout mLayout;
    private int xDelta;
    private int yDelta;

    private EditText mCommandLine;
    private LinearLayout mCommandLayout;
    private GestureDetector mGestureDetector;

    private List<Command> mCommandList;
    private RecyclerView mCommandRecycler;
    private CommandAdapter mCommandAdapter;

    private Button mCommandEnter;
    private List<String> mCommandLineList;
    private RecyclerView mCommandLineRecycler;
    private CommandLineAdapter mCommandLineAdapter;

    private List<SimDev> mSimDevList;
    private SimDev mSimDev;
    private TextView mSimDevName;
    private TextView mSimDevIpAddress;
    private ImageButton mSimDevImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation);

        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));

        initDeviceList();
        initCommandList();

        mAdapter = new MyAdapter(this, mDeviceList);
        mRecycler.setAdapter(mAdapter);
        mAdapter.setClickListener(this);

        mLayout = (RelativeLayout) findViewById(R.id.my_relative_layout);
        mCommandLine = (EditText) findViewById(R.id.command_line);
        mCommandLayout = (LinearLayout) findViewById(R.id.command_layout);

        mGestureDetector = new GestureDetector(this, new SingleTapConfirm());

        mCommandRecycler = (RecyclerView) findViewById(R.id.command_recycler);
        mCommandRecycler.setLayoutManager(new GridLayoutManager(this, 3));
        mCommandAdapter = new CommandAdapter(this, mCommandList);
        mCommandRecycler.setAdapter(mCommandAdapter);
        mCommandAdapter.setClickListener(this);

        mCommandEnter = (Button) findViewById(R.id.command_enter);
        mCommandEnter.setOnClickListener(onClickListener);

        mCommandLineList = new ArrayList<>();
        mCommandLineRecycler = (RecyclerView) findViewById(R.id.command_line_recycler);
        mCommandLineRecycler.setLayoutManager(new LinearLayoutManager(this));
        mCommandLineAdapter = new CommandLineAdapter(mCommandLineList);
        mCommandLineRecycler.setAdapter(mCommandLineAdapter);

        mSimDevList = new ArrayList<>();
        mLayout.setOnClickListener(onAreaClickListener);
        mSimDevName = (TextView) findViewById(R.id.sim_dev_name);
        mSimDevIpAddress = (TextView) findViewById(R.id.sim_dev_ip_address);

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String c = mCommandLine.getText().toString();
            mCommandLineList.add(c);
            mCommandLineAdapter.notifyDataSetChanged();

            String[] splited = c.split("\\s+");
            switch (splited[0]) {
                case "hostname":
                    mSimDev.setName(splited[1]);
                    break;
                case "ip":
                    mSimDev.setIpAddress(splited[2]);
                    mSimDevImage.setBackgroundResource(R.drawable.ic_desktop_green);
                    break;
            }
            updateInfo(mSimDev);
            mCommandLine.setText("");
        }
    };

    private View.OnClickListener onAreaClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mCommandLayout.setVisibility(View.GONE);
        }
    };

    @Override
    public void onItemClick(View view, int position) {
        final Device device = mDeviceList.get(position);
        addDevice(device);
    }

    @Override
    public void onCommandItemClick(View view, int position) {
        final Command command = mCommandList.get(position);
        mCommandLine.setText(command.getTitle());
        mCommandLine.setSelection(mCommandLine.getText().length());
    }

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();

            if (mGestureDetector.onTouchEvent(event)) {
                mSimDevImage = (ImageButton)v;
                mSimDev = ((SimDev) v.getTag());
                updateInfo(mSimDev);
                mCommandLayout.setVisibility(View.VISIBLE);
                return true;
            } else {
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
            }
            return false;
        }
    };

    private void initDeviceList() {
        mDeviceList = new ArrayList<>();
        mDeviceList.add(new Device(1, "Computer", 5, R.drawable.ic_desktop));
        mDeviceList.add(new Device(2, "Firewall", 4, R.drawable.ic_security));
        mDeviceList.add(new Device(3, "Router", 3, R.drawable.ic_router));
        mDeviceList.add(new Device(4, "Switch", 2, R.drawable.ic_videogame));
    }

    private void initCommandList() {
        mCommandList = new ArrayList<>();
        mCommandList.add(new Command(1, "ip address"));
        mCommandList.add(new Command(2, "hostname"));
        mCommandList.add(new Command(3, "gateway"));
    }

    private void addDevice(Device device) {
        int id = mSimDevList.size() + 1;
        SimDev simDev = new SimDev(id, device.getName() + id, device.getImageId(), null);
        mSimDevList.add(simDev);
        ImageButton mNewDeviceImage = new ImageButton(SimulationActivity.this);
        mNewDeviceImage.setTag(simDev);
        mNewDeviceImage.setBackgroundResource(device.getImageId());
        mNewDeviceImage.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));
        mNewDeviceImage.setOnTouchListener(onTouchListener);
        mLayout.addView(mNewDeviceImage);
    }

    private void updateInfo(SimDev simDev) {
        mSimDevName.setText(mSimDev.getName());
        mSimDevIpAddress.setText(mSimDev.getIpAddress());
    }

    private class SingleTapConfirm extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return true;
        }
    }
}
