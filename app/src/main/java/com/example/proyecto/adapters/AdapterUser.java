package com.example.proyecto.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto.R;
import com.example.proyecto.models.user;

import java.util.List;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.ViewHolder> {

    private List<user> Lista;

    public AdapterUser(List<user> lista) {
        Lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recyuser,null,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        user mi = Lista.get(position);
        holder.bind(mi);
    }

    @Override
    public int getItemCount() {
        return Lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tx1;
        TextView tx2;
        TextView tx3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tx1=itemView.findViewById(R.id.namecompleto);
            tx2=itemView.findViewById(R.id.email);
            tx3=itemView.findViewById(R.id.llave);
        }
        public void bind(user mi) {
            this.tx1.setText(mi.nombreCompleto());
            this.tx2.setText(mi.getEmail());
            this.tx3.setText(mi.getLlaveIngreso());
        }
    }
}
