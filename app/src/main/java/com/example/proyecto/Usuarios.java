package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.proyecto.adapters.AdapterRegistros;
import com.example.proyecto.adapters.AdapterUser;
import com.example.proyecto.models.Globalkeys;
import com.example.proyecto.models.Singleton;
import com.example.proyecto.models.nfc;
import com.example.proyecto.models.user;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Usuarios extends AppCompatActivity {

    RecyclerView recy;

    List<user> Lista;

    String key="aio_uWxJ550cjM8YoPoH1JXbQjBb28wm";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        String url;
        String permitidos=getIntent().getStringExtra("Permitido");

        if(permitidos.equals("Si")){
            url="http://sensorama.sytes.net/api/UsuariosPermitidos";

        }
        else{
            url="http://sensorama.sytes.net/api/UsuariosDenegados";
        }

        recy=findViewById(R.id.recycler);
        Lista=new ArrayList<>();

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(
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
                                user fff= new user();
                                fff.setName(jsonObject.getString("name").toString());
                                fff.setLastname(jsonObject.getString("lastname").toString());
                                fff.setEmail(jsonObject.getString("email").toString());
                                fff.setLlaveIngreso(jsonObject.getString("LlaveIngreso").toString());
                                Lista.add(fff);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        AdapterUser AP=new AdapterUser(Lista);
                        recy.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recy.setHasFixedSize(true);
                        recy.setAdapter(AP);
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
                headers.put("X-AIO-Key", Globalkeys.ADAFRUITkey);
                headers.put("Authorization", Globalkeys.JWTkey);
                return headers;
            }
        };
        Singleton.getInstance(this).addToRequestQue(jsonArrayRequest);

    }
}