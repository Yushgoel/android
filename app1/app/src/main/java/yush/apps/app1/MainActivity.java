package yush.apps.app1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText name;
    private EditText age;
    private EditText number;
    private EditText gender;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        name = (EditText) findViewById(R.id.etname);
        age = (EditText) findViewById(R.id.etage);
        gender = (EditText) findViewById(R.id.etgender);
        number = (EditText) findViewById(R.id.etnumber);
        submit = (Button) findViewById(R.id.button);

        submit.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Log.d("MAIN", "name=: "+ name.getText());
                                          Log.d("MAIN", "name=: "+ age.getText());
                                          Log.d("MAIN", "name=: "+ number.getText());
                                          Log.d("MAIN", "name=: "+ gender.getText());
                                      }
                                  }
        );

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

