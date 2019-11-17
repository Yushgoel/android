package yush.apps.blutooth_to_glove;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    Button connectGlove;
    TextView connect_success;

    private ArrayList<String> mDeviceList = new ArrayList<String>();
    private BluetoothAdapter mBluetoothAdapter;
    InputStream mmInputStream;

    BluetoothSocket mmSocket;
    BluetoothDevice mmDevice;
    private String remoteDeviceMac;
    private String address;

    ArrayList<BluetoothDevice> deviceArrayList;

    private static TextToSpeech mtts;

    Thread workerThread;
    volatile boolean stopWorker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectGlove = (Button) findViewById(R.id.connectbutton);
        connectGlove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Connect();
                }
                catch(Exception e)
                {
                    //pass
                }
            }
        });
        connect_success = (TextView) findViewById(R.id.connect_success);

        mtts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {

                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

        if (!pairedDevices.isEmpty()) {
            mmDevice = pairedDevices.iterator().next();

           /* deviceArrayList = new ArrayList<>();
            for (BluetoothDevice device : pairedDevices) {

                deviceArrayList.add(device);
            }*/
        }
    }

    private void Connect() throws IOException
    {

      //  BluetoothDevice mmDevice = deviceArrayList.get(0);
        Log.d("BT_TEST", "Device selected is " + mmDevice.getName());

        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); //Standard SerialPortService ID

        mmSocket = mmDevice.createInsecureRfcommSocketToServiceRecord(uuid);

        try{
            mmSocket.connect();
            Thread.sleep(5000);
            mmInputStream = mmSocket.getInputStream();

            Log.w("BT", "Receive data");

            connect_success.setText("ISpeak connected! You can now speak!");
            mtts.speak("I Speak Glove Connected! You can speak now!", TextToSpeech.QUEUE_FLUSH, null);
            Thread.sleep(2500);
            GetData()   ;
        }
        catch (IOException ioException){
            Log.w("BT", "Could not connect first time", ioException);
            connect_success.setText("Failed to connect to ISpeak. Try again");
            mtts.speak("Unable to connect to I Speak. Try again.", TextToSpeech.QUEUE_FLUSH, null);


        }
        catch (InterruptedException e1) {
            Log.w("BT", "THREAD Exception " + e1.getMessage(), e1);
            connect_success.setText("Failed to connect to ISpeak. Try again");
            mtts.speak("Unable to connect to I Speak. Try again.", TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    void GetData()
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
                            char a = (char) b;
                            if (b != 100) {
                                Log.d("Spica", " data byte " + b);
                            }
                            if (b == 49)
                            {
                                mtts.speak(Character.toString(a), TextToSpeech.QUEUE_FLUSH, null);
                                try{
                                    Thread.sleep(500);
                                }
                                catch(Exception e)
                                {

                                }
                            }
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
