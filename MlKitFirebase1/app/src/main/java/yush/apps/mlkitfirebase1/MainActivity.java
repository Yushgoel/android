package yush.apps.mlkitfirebase1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.vision.CameraSource;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.objects.FirebaseVisionObjectDetector;
import com.google.firebase.ml.vision.objects.FirebaseVisionObjectDetectorOptions;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    Bitmap image_bitmap;
    ImageView imageView;
    Button btmtakepic;
    TextView answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageview);
        btmtakepic = (Button) findViewById(R.id.takepicbtm);
        answer = (TextView) findViewById(R.id.answer);
        btmtakepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takepic();
            }
        });

    }
    void objectDetection(Bitmap imagebitmap)
    {
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(imagebitmap);
        FirebaseVisionTextRecognizer textRecognizer = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
        textRecognizer.processImage(image).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
            @Override
            public void onSuccess(FirebaseVisionText firebaseVisionText) {
                parsedata(firebaseVisionText);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("ERROR", e.toString());
            }
        });


    }

    private void parsedata(FirebaseVisionText text)
    {
        try {
            StringBuffer str = new StringBuffer();
            for (FirebaseVisionText.TextBlock txtBlock : text.getTextBlocks()) {
                for (FirebaseVisionText.Line line : txtBlock.getLines()) {

                    for (FirebaseVisionText.Element element : line.getElements()) {

                        str.append(element.getText());
                        str.append(",");
                        str.append(element.getConfidence());


                    }

                }
            }
            answer.setText(str.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void takepic()
    {
        try
        {
            Intent takepicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(takepicIntent.resolveActivity(getPackageManager()) != null)
            {
                startActivityForResult(takepicIntent, REQUEST_IMAGE_CAPTURE);
            }
        }catch (Exception e)
        {
            Log.d("ERROR", e.toString());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            image_bitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(image_bitmap);
            objectDetection(image_bitmap);

        }
    }
}
