package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.proyecto.models.Singleton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Inicio extends AppCompatActivity {

    //String key="aio_Tlne38m2vmEYPeDm0hfNnqDx8dja";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }

    public void user(View view) {
        Intent i = new Intent(this,Usuarios.class);
        view.getContext().startActivity(i);
    }
    public void reg(View view) {
        Intent i = new Intent(this,Registro.class);
        view.getContext().startActivity(i);
    }
    public void grafica(View view) {
        Intent i = new Intent(this,Grafica.class);
        view.getContext().startActivity(i);
    }
    public void Accion1(View view) {
        String url1 ="https://io.adafruit.com//api/v2/CarlosLpz/feeds/nfcactivado/data";

        JSONObject datos = new JSONObject();
        try {
            datos.put("value","1");
        } catch (JSONException e){
            e.printStackTrace();
        }

        JsonObjectRequest carta1 = new JsonObjectRequest(Request.Method.POST, url1, datos, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                final Gson gson = new Gson();

                try {
                    JSONArray PokeJson = response.getJSONArray("results");
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
    public void nfctext(View view){
        Intent i = new Intent(this,nfctext.class);
        view.getContext().startActivity(i);
    }
}