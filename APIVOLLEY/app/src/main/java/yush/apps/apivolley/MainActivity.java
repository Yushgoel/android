package yush.apps.apivolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewResult;
    private RequestQueue mQueue;
    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = findViewById(R.id.request_result_txt);
        Button buttonParse = findViewById(R.id.button_parse);
        input = (EditText) findViewById(R.id.Input);
        mQueue = Volley.newRequestQueue(this);
        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });
    }
    private void jsonParse(){
        // String url = "https://api45.myjson.com/bins/kp9wz";
        String country = input.getText().toString();
        if(TextUtils.isEmpty(country))
        {
            mTextViewResult.setError("Please enter a valid country name");
            return;
        }
        country = country.toLowerCase();
        String url = "http://countryapi.gear.host/v1/Country/getCountries?pName=" + country;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    // JSONArray jsonArray = response.getJSONArray("employees");
                    JSONArray jsonArray = response.getJSONArray("Response");
                    Gson gson = new Gson();
                    for(int i = 0; i < jsonArray.length(); i++)
                    {
                        // JSONObject employee = jsonArray.getJSONObject(i);
                        JSONObject locationObj = jsonArray.getJSONObject(i);
                        //String name = location.getString("Name");
                        //String subRegion = location.getString("SubRegion");
                        //String currency = location.getString("CurrencyName");
                        //String currency_symbol = location.getString("CurrencySymbol");

                        //Location loc = new Location();
                        //loc.name = locationObj.getString("Name");
                        //loc.subRegion = locationObj.getString("SubRegion");
                        //loc.currency = locationObj.getString("CurrencyName");
                        //loc.currency_symbol = locationObj.getString("CurrencySymbol");
                        //int age = location.getInt("age");
                        //String mail = location.getString("mail");
                        Log.d("log", locationObj.toString());
                        Location loc = gson.fromJson(locationObj.toString(), Location.class);

                        // mTextViewResult.append(name + ", " + String.valueOf(age) + mail + "\n\n");
                        mTextViewResult.append(loc.name + ", " + loc.subRegion + ", Currency: " + loc.currency_symbol + " " + loc.currency + "\n\n");
                    }
                }
                catch(JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                mTextViewResult.setText("");
                mTextViewResult.setText(error.toString());

            }
        });
        mQueue.add(request);

    }
}
