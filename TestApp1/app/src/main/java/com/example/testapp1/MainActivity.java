package com.example.testapp1;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> mDeviceList = new ArrayList<String>();
    private BluetoothAdapter mBluetoothAdapter;
    InputStream mmInputStream;
    TextView myLabel;

    BluetoothSocket mmSocket;
    BluetoothDevice mmDevice;
    private String remoteDeviceMac;
    private String address;

    Thread workerThread;


    volatile boolean stopWorker;

    ArrayList<BluetoothDevice> deviceArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("BT_TEST", "onCreate");
        listView = (ListView) findViewById(R.id.listView);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        ArrayAdapter<String> btArrayAdapter
                = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);

        myLabel = (TextView)findViewById(R.id.txLabel);
     
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

        if (pairedDevices.size() > 0) {
            deviceArrayList = new ArrayList<>();
            for (BluetoothDevice device : pairedDevices) {

                deviceArrayList.add(device);
                String deviceBTName = device.getName();
                btArrayAdapter.add(deviceBTName + ": " + device.getAddress());
            }
        }

        listView.setAdapter(btArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                try{
                    openAndConnectBT(position);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    void openAndConnectBT(int pos) throws IOException
    {
        BluetoothDevice mmDevice = deviceArrayList.get(pos);
        Log.d("BT_TEST", "Device selected is " + mmDevice.getName());

        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); //Standard SerialPortService ID

        mmSocket = mmDevice.createInsecureRfcommSocketToServiceRecord(uuid);

        try{
            mmSocket.connect();
            Thread.sleep(5000);
            mmInputStream = mmSocket.getInputStream();

            Log.w("BT", "Receive data");
            beginListenForData();

            myLabel.setText("Bluetooth Opened");
        }
        catch (IOException ioException){
            Log.w("BT", "Could not connect first time", ioException);
        }
        catch (InterruptedException e1) {
            Log.w("BT", "THREAD Exception " + e1.getMessage(), e1);
        }
    }

    void beginListenForData()
    {

        Log.d("TAG", " beginListenForData() - ");

        stopWorker = false;


        workerThread = new Thread(new Runnable()
        {
            public void run()
            {
                while(!Thread.currentThread().isInterrupted() && !stopWorker)
                {
                    try
                    {
                        int bytesAvailable = 2;

                        byte[] packetBytes = new byte[bytesAvailable];
                        mmInputStream.read(packetBytes);

                        for(int i=0;i<bytesAvailable;i++)
                        {
                            byte b = packetBytes[i];
                            if (b != 59)
                                Log.d("TAG", " data byte " + b);

                        }

                    }
                    catch (IOException ex)
                    {
                        stopWorker = true;
                    }
                }
            }
        });

        workerThread.start();
    }
}
