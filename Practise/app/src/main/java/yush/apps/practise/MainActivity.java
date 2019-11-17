package yush.apps.practise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //        Mobile phone = new Mobile();
//        phone.setvalues(404, 10, "vivo");
//        phone.get_details();
//
//        Product phone2 = new Mobile();
        Dog bosco = new Dog();
        bosco.animalBreathe();
        bosco.animalMove();

        Fish nemo = new Fish();
        nemo.animalBreathe();
        nemo.animalMove();
    }

}
