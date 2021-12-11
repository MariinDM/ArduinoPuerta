package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.proyecto.adapters.AdapterRegistros;
import com.example.proyecto.models.Distancia;
import com.example.proyecto.models.Singleton;
import com.example.proyecto.models.nfc;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class Grafica extends AppCompatActivity {

    RecyclerView recy;
    TextView tx;
    LineChartView chart;
    List<Line> lines = new ArrayList<Line>();
    List<Distancia> Lista;
    //String key="aio_Tlne38m2vmEYPeDm0hfNnqDx8dja";
    int size;
    LineChartData data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafica);

        APIdistancia();
    }

    public void APIdistancia() {
        String url2="https://io.adafruit.com//api/v2/CarlosLpz/feeds/verdistancia/data?limit=10";
        Lista=new ArrayList<>();

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(
                Request.Method.GET,
                url2,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        size=response.length();
                        Lista.clear();
                        for (int i=0;i<size;i++){
                            try {
                                JSONObject jsonObject=response.getJSONObject(i);
                                Distancia fff= new Distancia();
                                fff.setValue(jsonObject.getString("value").toString());
                                Lista.add(fff);
                                GenerarGrafico();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
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
                return headers;
            }
        };
        Singleton.getInstance(this).addToRequestQue(jsonArrayRequest);
        //String msg = Lista.size() + "";
        //Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

    }

    public void GenerarGrafico(){


        chart=findViewById(R.id.chart);

        List<PointValue> values = new ArrayList<PointValue>();

        for (int i=0; i <= Lista.size()-1; i++){
            values.add(new PointValue(i, Lista.get(i).GetIntValue()));
        }

        //In most cased you can call data model methods in builder-pattern-like manner.
        Line line = new Line(values).setColor(Color.BLUE).setCubic(true);

        line.setStrokeWidth(2);
        line.setPointRadius(0);
        lines.add(line);

        data = new LineChartData();
        data.setLines(lines);

        //LineChartView chart = new LineChartView(getApplicationContext());
        chart.setLineChartData(data);
        Contador();

    }

    public void Contador(){
        int lapso = 1000;
        int tiempoTot = 10000;
        new CountDownTimer(lapso,tiempoTot) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                data.getLines().clear();
                //chart.setLineChartData(data);
                APIdistancia();

            }
        }.start();
    }
}