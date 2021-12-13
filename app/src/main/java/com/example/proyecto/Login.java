package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.proyecto.models.Globalkeys;
import com.example.proyecto.models.Singleton;
import com.example.proyecto.models.nfc;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    //VIEWS
    Button iniciar;
    EditText Correo_et;
    EditText Contra_et;
    //VARIABLES
    String correo;
    String contra;
    String llave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Correo_et = findViewById(R.id.editText);
        Contra_et = findViewById(R.id.editText2);

        Button button = (Button) findViewById(R.id.iniciar_btn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                enviarInfo();
            }
        });

    }

    private void enviarInfo() {
        //Conseguir variables
        correo = Correo_et.getText().toString();
        contra = Contra_et.getText().toString();

        String url1 ="http://sensorama.sytes.net/api/login";
        String key="llavejwt"; //NO NECESARIA EN REGISTER

        JSONObject datos = new JSONObject();
        try {
            datos.put("email",correo);
            datos.put("password",contra);

        } catch (JSONException e){
            e.printStackTrace();
        }

        JsonObjectRequest carta1 = new JsonObjectRequest(Request.Method.POST, url1, datos, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    llave = response.getString("token");
                    //Toast.makeText(getApplicationContext(), llave, Toast.LENGTH_SHORT).show();
                    CambiarIntent();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "CORREO O CONTRASEÑA INCORRECTOS", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", key);
                return headers;
            }
        };

        Singleton.getInstance(this).addToRequestQue(carta1);
    }

    private void CambiarIntent() {
        Intent i = new Intent(Login.this,Inicio.class);
        Globalkeys.JWTkey = llave;
        startActivity(i);
    }
}