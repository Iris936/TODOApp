package com.example.todoapp.UI.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.Models.Presupuesto;
import com.example.todoapp.R;
import com.example.todoapp.UI.viewHolders.ChildViewHolder;
import com.example.todoapp.UI.viewHolders.MainViewHolder;

import java.util.List;

public class ChildRecyclerAdapter extends RecyclerView.Adapter<ChildViewHolder>{

    List<Presupuesto> items;
    private final OnItemChildClickListener onItemChildClickListener;

    public ChildRecyclerAdapter(List<Presupuesto> items, OnItemChildClickListener onItemChildClickListener) {
        this.items = items;
        this.onItemChildClickListener = onItemChildClickListener;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.item_row, parent, false);
        return new ChildViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder holder, int position) {
        holder.txvItemRow.setText(items.get(position).getNombre());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemChildClickListener != null){
                    Presupuesto mainObject = items.get(holder.getAdapterPosition());
                    onItemChildClickListener.onItemChildClick(mainObject);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnItemChildClickListener{
        void onItemChildClick(Presupuesto mainObject);
    }
}
