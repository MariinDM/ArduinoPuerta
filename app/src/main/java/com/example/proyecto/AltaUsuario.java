package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.proyecto.models.Globalkeys;
import com.example.proyecto.models.Singleton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AltaUsuario extends AppCompatActivity {

    //VIEWS
    EditText nombre;
    EditText apellido;
    EditText correo;
    EditText clave;
    EditText contra;
    EditText contraConf;
    Switch permiso;
    Button crear;

    //VARIABLES
    String switchState;
    String nombre_s;
    String apellido_s;
    String correo_s;
    String clave_s;
    String contra_s;
    String contraConf_s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_usuario);

        //ENCONTRAR ID
        nombre = findViewById(R.id.nombre_et);
        apellido = findViewById(R.id.apellido_et);
        correo = findViewById(R.id.correo_et);
        clave = findViewById(R.id.nfc_et);
        contra = findViewById(R.id.pswd_et);
        contraConf = findViewById(R.id.cpswd_et);
        permiso = findViewById(R.id.permiso_switch);
        crear = findViewById(R.id.crear_btn);

        //ACCIONES
        crear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ConfirmarErrores();
            }
        });
    }

    public void ConfirmarErrores() {
        //Conseguir variables
        nombre_s = nombre.getText().toString();
        apellido_s = apellido.getText().toString();
        correo_s = correo.getText().toString();
        clave_s = clave.getText().toString();
        contra_s = contra.getText().toString();
        contraConf_s = contraConf.getText().toString();


        if (permiso.isChecked()){
            switchState = "1";
        } else {switchState = "0";}

        if(contra_s.length() < 6){
            Toast.makeText(getApplicationContext(), "La contraseña debe incluir al menos 6 caracteres", Toast.LENGTH_SHORT).show();
        } else if (!contra_s.equals(contraConf_s)){
            Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        } else {
            SubirInfo();
        }
    }

    public void SubirInfo(){

        String url1 ="http://sensorama.sytes.net/api/register";

        JSONObject datos = new JSONObject();
        try {
            datos.put("name",nombre_s);
            datos.put("lastname",apellido_s);
            datos.put("email",correo_s);
            datos.put("password",contra_s);
            datos.put("password_confirmation",contraConf_s);
            datos.put("LlaveIngreso",clave_s);
            datos.put("permiso",switchState);


        } catch (JSONException e){
            e.printStackTrace();
        }

        JsonObjectRequest carta1 = new JsonObjectRequest(Request.Method.POST, url1, datos, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), "USUARIO REGISTRADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Clave o correo ya existentes", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", Globalkeys.ADAFRUITkey);
                return headers;
            }
        };

        Singleton.getInstance(this).addToRequestQue(carta1);
    }
}