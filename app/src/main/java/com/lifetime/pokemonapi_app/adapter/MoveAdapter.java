package com.lifetime.pokemonapi_app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lifetime.pokemonapi_app.R;
import com.lifetime.pokemonapi_app.model.MoveInfo;

import java.util.ArrayList;

public class MoveAdapter extends RecyclerView.Adapter<MoveAdapter.ViewHolder> {

    ArrayList<MoveInfo> moveInfos;

    public MoveAdapter(ArrayList<MoveInfo> moveInfos){
        this.moveInfos = moveInfos;
    }

    @NonNull
    @Override
    public MoveAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.move_item_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoveAdapter.ViewHolder holder, int position) {
        holder.bindView(moveInfos.get(position));
    }

    @Override
    public int getItemCount() {
        return moveInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView moveName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            moveName = itemView.findViewById(R.id.move_name);
        }

        public void bindView(MoveInfo moveInfo){
            moveName.setText(moveInfo.getName());
        }
    }
}
