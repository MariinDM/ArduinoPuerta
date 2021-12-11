package com.example.proyecto.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto.R;
import com.example.proyecto.Registro;
import com.example.proyecto.models.nfc;

import java.util.List;

public class AdapterRegistros extends RecyclerView.Adapter<AdapterRegistros.ViewHolder> {

    List<nfc> Lista;

    public AdapterRegistros(List<nfc> lista) {
        Lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.registro,null,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        nfc mi=Lista.get(position);
        holder.bind(mi);
    }

    @Override
    public int getItemCount() {
        return Lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView value;
        TextView created_at;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            value=itemView.findViewById(R.id.tx1);
            created_at=itemView.findViewById(R.id.tx2);
        }
        public void bind(nfc mi) {
            this.value.setText(mi.getValue());
            this.created_at.setText(mi.getCreated_at());
        }
    }
}
