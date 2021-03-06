package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.proyecto.adapters.AdapterRegistros;
import com.example.proyecto.models.Singleton;
import com.example.proyecto.models.nfc;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Registro extends AppCompatActivity {

    RecyclerView recy;
    List<nfc> Lista;

    String key="aio_uWxJ550cjM8YoPoH1JXbQjBb28wm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        String url="http://sensorama.sytes.net/api/RegistrosEntradas";
        recy=findViewById(R.id.recy);
        Lista=new ArrayList<>();

        /*JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int size=response.length();
                        for (int i=0;i<size;i++){
                            try {
                                JSONObject jsonObject=response.getJSONObject(i);
                                nfc fff= new nfc();
                                fff.setValue(jsonObject.getString("value").toString());
                                fff.setCreated_at(jsonObject.getString("created_at").toString());
                                Lista.add(fff);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        AdapterRegistros AP= new AdapterRegistros(Lista);
                        recy.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recy.setHasFixedSize(true);
                        recy.setAdapter(AP);
                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("X-AIO-Key", key);
                headers.put("Authorization", "bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9zZW5zb3JhbWEuc3l0ZXMubmV0XC9hcGlcL2xvZ2luIiwiaWF0IjoxNjM5MzMwNjgwLCJleHAiOjE2MzkzMzQyODAsIm5iZiI6MTYzOTMzMDY4MCwianRpIjoiSlZZT3YyNzlXWnVZWHdiVyIsInN1YiI6MTYsInBydiI6IjIzYmQ1Yzg5NDlmNjAwYWRiMzllNzAxYzQwMDg3MmRiN2E1OTc2ZjcifQ.q-rMdj3KGoqtXg_G-trKks8UO_zEc3aE5UTCfxUE62k");
                return headers;
            }
        };
        Singleton.getInstance(this).addToRequestQue(jsonArrayRequest);*/
    }
}