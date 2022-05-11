package com.isil.finalappplap.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.isil.finalappplap.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ProveedoresAdapter extends RecyclerView.Adapter<ProveedoresAdapter.ViewHolder> {
    ArrayList<HashMap<String,String>> arrayList;
    private static ItemClickListener itemClickListener;
    public ProveedoresAdapter(ArrayList<HashMap<String,String>> arrayList) {
        this.arrayList = arrayList;

    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        ProveedoresAdapter.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_proveedor,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HashMap<String,String> map = arrayList.get(position);

        holder.mtvProveedorEmpresa.setText(map.get("empresa"));
        holder.mtvProveedorNombres.setText(map.get("nombres"));
        holder.mtvProveedorCargo.setText(map.get("cargo"));
        holder.mtvProveedorPais.setText(map.get("pais"));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mtvProveedorEmpresa, mtvProveedorNombres, mtvProveedorCargo, mtvProveedorPais;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mtvProveedorEmpresa = itemView.findViewById(R.id.tvEmpresa);
            mtvProveedorNombres = itemView.findViewById(R.id.tvNombres);
            mtvProveedorCargo = itemView.findViewById(R.id.tvCargo);
            mtvProveedorPais = itemView.findViewById(R.id.tvPais);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Toast.makeText(itemView.getContext(),
                    String.valueOf(this.getLayoutPosition()),Toast.LENGTH_SHORT).show();
            itemClickListener.onItemClick(this.getLayoutPosition());
        }
    }
    public interface ItemClickListener{
        void onItemClick(int position);
    }
}
