package yush.apps.infixtopostfix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    TextView postOut;
    EditText infixIn;
    Button submit;
    Stack<String> operandStack;
    String[] output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        postOut = (TextView) findViewById(R.id.postOut);
        infixIn = (EditText) findViewById(R.id.InfixIn);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePostFix();
            }
        });
    }

    void calculatePostFix()
    {
        String input = infixIn.getText().toString();
        String[] output;
    }
}
