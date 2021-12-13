package com.example.proyecto.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.proyecto.Permisos;
import com.example.proyecto.R;
import com.example.proyecto.models.Singleton;
import com.example.proyecto.models.nfc;
import com.example.proyecto.models.user;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdapterPermisos extends RecyclerView.Adapter<AdapterPermisos.ViewHolder> {

    List<user> Lista;

    public AdapterPermisos(List<user> lista) {
        Lista = lista;
    }

    @NonNull
    @Override
    public AdapterPermisos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.permisos,null,false);
        return new AdapterPermisos.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPermisos.ViewHolder holder, int position) {
        user mi=Lista.get(position);
        holder.bind(mi);
    }

    @Override
    public int getItemCount() {
        return Lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tx1;
        Button btn1;
        Button btn2;
        user us;

        String key="aio_uWxJ550cjM8YoPoH1JXbQjBb28wm";

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tx1=itemView.findViewById(R.id.tx1);
            btn1=itemView.findViewById(R.id.btn1);
            btn2=itemView.findViewById(R.id.btn2);
            btn1.setOnClickListener(this);
            btn2.setOnClickListener(this);
        }
        public void bind(user mi) {
            this.tx1.setText(mi.nombreCompleto());
            us=mi;
        }

        @Override
        public void onClick(View view) {
            if (btn1.callOnClick()){

            }else if(btn2.callOnClick()){

            }

        }
    }
}
