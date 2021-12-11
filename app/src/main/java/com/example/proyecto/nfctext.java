package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.proyecto.models.Singleton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.HashMap;
import java.util.Map;

public class nfctext extends AppCompatActivity {

    //String key="aio_Tlne38m2vmEYPeDm0hfNnqDx8dja";
    EditText et1;
    String nfc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfctext);

        et1=findViewById(R.id.et1);
    }

    public void enviar(View view) {

        nfc=et1.getText().toString();

        String url ="http://3.13.226.253/api/ChecarManual";

        JSONObject datos = new JSONObject();
        try {
            datos.put("LlaveIngreso",nfc);
        } catch (JSONException e){
            e.printStackTrace();
        }

        JsonObjectRequest carta1 = new JsonObjectRequest(
                Request.Method.POST,
                url,
                datos,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String stringer=response.getString("respuesta");
                    Toast.makeText(getApplicationContext(), stringer, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("X-AIO-Key", key);
                return headers;
            }
        };

        Singleton.getInstance(this).addToRequestQue(carta1);
    }

}