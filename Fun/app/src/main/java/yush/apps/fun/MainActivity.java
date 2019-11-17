package yush.apps.fun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView pic1;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pic1 = (ImageView) findViewById(R.id.img1);
        button = (Button) findViewById(R.id.btnMove);
        button.setOnClickListener(listener);

    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Animation animation = new TranslateAnimation(pic1.getX(), 500,pic1.getY(), 1000);
            animation.setDuration(1500);
            animation.setFillAfter(true);

            pic1.startAnimation(animation);

        }
    };


}
