package br.com.up.caronaup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class CompraCreditoActivity extends AppCompatActivity {

    private Spinner spinner;
    private ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra_credito);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        final String url = "http://carpool.myyem5fiks.us-west-2.elasticbeanstalk.com/buscarCampus";


        final TextView textoText = (TextView) findViewById(R.id.txt_teste);
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Response", response.toString());

                textoText.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response", error.toString());
            }
        });
        queue.add(getRequest);
        queue.start();


        spinner = (Spinner) this.

                findViewById(R.id.spinner);

        adapter = ArrayAdapter.createFromResource(

                getApplicationContext(), R

                        .array.values_money, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

                                          {
                                              @Override
                                              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                  Toast.makeText(getApplicationContext(), parent.getItemIdAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
                                              }

                                              @Override
                                              public void onNothingSelected(AdapterView<?> parent) {
                                              }
                                          }

        );

        getSupportActionBar()

                .

                        setDisplayHomeAsUpEnabled(true);
    }

}
