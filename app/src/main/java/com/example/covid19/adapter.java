package com.example.covid19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {
    ArrayList<model> list;
    Context context;

    public adapter(ArrayList<model> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        model mod=list.get(position);
        holder.cnfrmm.setText(mod.getConfirmed());
        holder.active.setText(mod.getActive());
        holder.recover.setText(mod.getRecovered());
        holder.state.setText(mod.getState());
        holder.deat.setText(mod.getDeaths());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView state,deat,recover,active,cnfrmm;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            state=itemView.findViewById(R.id.st);
            deat=itemView.findViewById(R.id.deat);
            recover=itemView.findViewById(R.id.recv);
            active=itemView.findViewById(R.id.actv);
            cnfrmm=itemView.findViewById(R.id.cnf);
        }

    }
}
