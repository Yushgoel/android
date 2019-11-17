package yush.apps.sendbluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothDevice mmDevice;
    private BluetoothSocket mmSocket;

    private Button connect, btmOn, btmOff;
    OutputStream mmOutputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connect = (Button) findViewById(R.id.connect);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Connect();
                }
                catch (IOException e)
                {
                    Log.d("Logstuff", "it isn't going to work");
                }
            }
        });
        btmOn = (Button) findViewById(R.id.on);
        btmOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blOn();
            }
        });


        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        Set<BluetoothDevice> devices = mBluetoothAdapter.getBondedDevices();

        if (!devices.isEmpty())
        {
            mmDevice = devices.iterator().next();
            Log.d("Logstuff", mmDevice.getName());
        }
    }

    private void Connect() throws IOException
    {
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
        mmSocket = mmDevice.createInsecureRfcommSocketToServiceRecord(uuid);
        try
        {
            mmSocket.connect();

        }
        catch (Exception e)
        {
            Log.d("Logstuff", "Failed. Now sit and cry cause it aint working");
        }
    }
    private void blOn()
    {
        try {
        mmOutputStream = mmSocket.getOutputStream();
        mmOutputStream.write("1".getBytes());
        }
        catch (IOException e)
        {
            Log.d("Logstuff", "It is not working so go home");
        }
    }
    private void blOff()
    {
        try {
            mmOutputStream = mmSocket.getOutputStream();
            mmOutputStream.write("0".getBytes());
        }
        catch (IOException e)
        {
            Log.d("Logstuff", "It is not working so go home");
        }
    }
}
