package yush.apps.listviewtest;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ListView listview;
    private ArrayAdapter listviewadap;
    private List<String> data;
    private Button btmAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btmAdd = (Button) findViewById(R.id.btmAdd);

        data = new ArrayList<String>();

        data.add("oh my god");
        data.add("ok");
        data.add("item1");


        btmAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });

        listviewadap = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);

        listview = (ListView) findViewById(R.id.list);
        listview.setAdapter(listviewadap);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Click", ((TextView)view).getText().toString() + "    " + position + "    " + id);

                data.remove(position);
                listviewadap.notifyDataSetChanged();
            }
        });

    }

    private void add()
    {
        data.add("Congratulations you have added an element! YIPEE your code works");

        listviewadap.notifyDataSetChanged();
    }
}
