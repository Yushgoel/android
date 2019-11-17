package yush.apps.firebase_push;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText inp_name;
    EditText inp_marks;
    EditText inp_age;
    Button submit;

    Student student;
    String name;
    int marks;
    int age;
    int id = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inp_name = (EditText) findViewById(R.id.name_inp);
        inp_marks = (EditText) findViewById(R.id.marks_inp);
        inp_age = (EditText) findViewById(R.id.age_inp);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                take_data();
            }
        });
    }

    private void take_data() {
        name = inp_name.getText().toString();
        marks = Integer.parseInt(inp_marks.getText().toString());
        age = Integer.parseInt(inp_age.getText().toString());
        Student student = new Student();
        student.setData(marks, name, age);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

     //   Map<String, Student> student_stuff = new HashMap<>();
      //  student_stuff.put(id+"", student);
        myRef.child("Students").child(id+"").setValue(student);
        id++;
    }
}
