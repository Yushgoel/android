package yush.apps.find_phone;

import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaRecorder mRecorder = null;
    private double loudness = 0;

    public Button stopper;
    public Button starter;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stopper = (Button) findViewById(R.id.stop);
        starter = (Button) findViewById(R.id.starte);
        stopper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });
        starter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setup();
            }
        });

    }

    public double getAmplitude() {
        if (mRecorder != null)
            return  mRecorder.getMaxAmplitude();
        else
            return 0;

    }

    public void setup()
    {
        if (mRecorder == null)
        {
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mRecorder.setOutputFile("/dev/null");
            try{
                mRecorder.prepare();
            }
            catch (IOException e)
            {
                Log.d("AMPLITUDE Error", "IOException = " + e.toString());
                //pass
            }
            mRecorder.start();
         //   stop();
        }

    }
    public void stop() {
        if (mRecorder != null) {

            loudness = getAmplitude();
            Log.d("AMPLITUDE val = ", loudness+"");
            if (loudness > 1.0)
            {
                Log.d("AMPLITUDE louder than 1 ", "Working finally alas my lads, god has blessed us");
            }

            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;

        }
    }



}
